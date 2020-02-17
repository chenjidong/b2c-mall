package com.ppepper.payment.service;

import com.ppepper.common.dto.AccountDTO;
import com.ppepper.common.dto.OrderDTO;
import com.ppepper.common.dto.PaymentDTO;
import com.ppepper.common.enums.AccountStatusType;
import com.ppepper.common.enums.OrderStatusType;
import com.ppepper.common.enums.PaymentStatusType;
import com.ppepper.common.enums.PaymentType;
import com.ppepper.common.feign.AccountSysFeignService;
import com.ppepper.common.feign.OrderSysFeignService;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import com.ppepper.payment.domain.PaymentDO;
import com.ppepper.payment.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

/**
 * Created with ChenJiDong
 * Created By 2020-02-13
 */
@Service
public class PaymentServiceImpl extends BaseServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private AccountSysFeignService accountSysFeignService;

    @Autowired
    private OrderSysFeignService orderSysFeignService;

    @Override
    public AjaxResult getByPayNo(Long accountId, String payNo) {
        PaymentDO paymentDO = new PaymentDO();
        paymentDO.setAccountId(accountId);
        paymentDO.setPayNo(payNo);
        return toAjax(copyProperties(paymentMapper.selectOne(paymentDO), PaymentDTO.class));
    }

    @Override
    public AjaxResult getByOrderNo(Long accountId, String orderNo) {
        PaymentDO paymentDO = new PaymentDO();
        paymentDO.setAccountId(accountId);
        paymentDO.setOrderNo(orderNo);
        return toAjax(copyProperties(paymentMapper.selectOne(paymentDO), PaymentDTO.class));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public AjaxResult create(Long accountId, String orderNo, String channel, Integer payType) {
        AccountDTO accountDTO = accountSysFeignService.get(accountId);
        if (accountDTO.getStatus() != AccountStatusType.ENABLE.getCode())
            return error("账号已被冻结  无法支付");


        PaymentDO paymentDO = new PaymentDO();
        paymentDO.setAccountId(accountId);
        paymentDO.setOrderNo(orderNo);
        paymentDO = paymentMapper.selectOne(paymentDO);

        if (paymentDO != null) {
            if (paymentDO.getStatus() == PaymentStatusType.CANCEL.getCode())
                return error("订单已取消");

            if (paymentDO.getStatus() != PaymentStatusType.UNPAY.getCode())
                return error("订单已支付或退款中,无需重复支付");
        }

        OrderDTO orderDTO = orderSysFeignService.getByOrderNo(orderNo);


        if (payType == null)
            payType = PaymentType.WECHAT.getCode();

        Object point = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        try {
            if (payType == PaymentType.OFFLINE.getCode()) {
                int count = 0;
                if (paymentDO == null) {
                    paymentDO = new PaymentDO();

                    paymentDO.setAccountId(accountId);
                    paymentDO.setOrderNo(orderNo);
                    paymentDO.setChannel(channel);
                    paymentDO.setAmount(orderDTO.getActualPrice());
                    paymentDO.setStatus(PaymentStatusType.PAYING.getCode());
                    paymentDO.setGmtCreate(new Date());
                    paymentDO.setGmtUpdate(new Date());
                    count = paymentMapper.insert(paymentDO);
                } else {
                    paymentDO.setStatus(PaymentStatusType.PAYING.getCode());
                    count = paymentMapper.updateById(paymentDO);
                }
                if (count > 0) {

                    Boolean success = orderSysFeignService.setStatus(orderNo, OrderStatusType.MANUAL_PAYING.getCode());
                    if (!success)
                        throw new RuntimeException();
                    return success("线下支付完成！");
                } else throw new RuntimeException();

            } else if (payType == PaymentType.WECHAT.getCode()) {

            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(point);
        }

        return error("生成支付订单失败");
    }
}
