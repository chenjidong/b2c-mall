package com.ppepper.order.quartz;

import com.ppepper.common.dto.OrderDTO;
import com.ppepper.common.enums.OrderStatusType;
import com.ppepper.common.feign.CouponFeignService;
import com.ppepper.common.feign.GoodsFeignService;
import com.ppepper.common.redis.LockComponent;
import com.ppepper.order.domain.OrderDO;
import com.ppepper.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with ChenJiDong
 * Created By 2020-02-16
 */
@Component
@EnableScheduling
public class CheckQuartz {
    private static final Logger logger = Logger.getLogger(CheckQuartz.class.getSimpleName());
    private static final String ORDER_STATUS_LOCK = "ORDER_STATUS_QUARTZ_LOCK";

    @Autowired
    private OrderService orderService;

    @Autowired
    private LockComponent lockComponent;

    @Autowired
    private GoodsFeignService goodsFeignService;

    @Autowired
    private CouponFeignService couponFeignService;

    /**
     * 5分钟执行一次
     */
    @Scheduled(cron = "0 */5 * * * ?")
    @Transactional(rollbackFor = {Exception.class})
    public void checkOrderStatus() {
        if (lockComponent.tryLock(ORDER_STATUS_LOCK, 60)) {
            Date now = new Date();

            Object point = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
            try {

                List<OrderDTO> list = orderService.selectExpireOrderNos(OrderStatusType.UNPAY.getCode(), new Date(now.getTime() - 1000 * 60 * 30));
                if (!CollectionUtils.isEmpty(list)) {

                    list.forEach(item -> {

                        if (lockComponent.tryLock(OrderService.ORDER_STATUS_LOCK + item.getOrderNo(), 30)) {
                            OrderDO orderDO = new OrderDO();
                            orderDO.setStatus(OrderStatusType.CANCELED_SYS.getCode());
                            orderDO.setOrderNo(item.getOrderNo());
                            orderDO.setGmtUpdate(now);
                            int count = orderService.update(orderDO);
                            if (count <= 0)
                                throw new RuntimeException();

                            //回滚优惠券
                            if (item.getCouponId() != null) {
                                Boolean rollbackCoupon = couponFeignService.rollbackUnused(item.getAccountId(), item.getCouponId());
                                if (!rollbackCoupon)
                                    throw new RuntimeException();
                            }

                            //回滚商品库存
                            if (!CollectionUtils.isEmpty(item.getSkuList())) {
                                item.getSkuList().forEach(item1 -> {
                                    Boolean rollbackStock = goodsFeignService.rollbackStock(item1.getSkuId(), item1.getNum());
                                    if (!rollbackStock)
                                        throw new RuntimeException();
                                });
                            }
                        }
                    });

                    logger.info("checkOrderStatus() - 耗时：" + (System.currentTimeMillis() - now.getTime()) + " ms");
                }
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(point);
            } finally {
                lockComponent.release(ORDER_STATUS_LOCK);
            }
        }

    }
}
