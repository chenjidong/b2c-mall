package com.ppepper.account.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ppepper.account.domain.CartDO;
import com.ppepper.account.mapper.CartMapper;
import com.ppepper.common.dto.CartDTO;
import com.ppepper.common.dto.SpuDTO;
import com.ppepper.common.enums.SpuStatusType;
import com.ppepper.common.feign.GoodsFeignService;
import com.ppepper.common.model.AjaxResult;
import com.ppepper.common.service.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-11
 */
@Service
public class CartServiceImpl extends BaseServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private GoodsFeignService goodsFeignService;

    @Override
    public AjaxResult get(Long accountId, Long id) {
        CartDO cartDO = new CartDO();
        cartDO.setAccountId(accountId);
        cartDO.setId(id);

        cartDO = cartMapper.selectOne(cartDO);
        if (cartDO == null)
            return error("商品不存在！");
        CartDTO cartDTO = copyProperties(cartDO, CartDTO.class);
        SpuDTO spuDTO = goodsFeignService.getBySkuIds(cartDO.getSkuId());
        if (spuDTO != null) {
            cartDTO.setSpuDTO(spuDTO);
        }
        return toAjax(cartDTO);
    }

    @Override
    public AjaxResult list(Long accountId, Integer pageNo, Integer pageSize) {
        List<CartDO> list = cartMapper.selectPage(new RowBounds((pageNo - 1) * pageSize, pageSize), new EntityWrapper<CartDO>().eq("account_id", accountId));
        if (list != null && !list.isEmpty()) {
            List<CartDTO> cartDTOS = copyListProperties(list, CartDTO.class);
            List<Long> skuIds = new ArrayList<>();
            cartDTOS.forEach(item -> {
                skuIds.add(item.getSkuId());
            });

            List<SpuDTO> spuDTO = goodsFeignService.getBySkuIds(skuIds.toArray(new Long[]{}));
            if (spuDTO != null && !spuDTO.isEmpty()) {
                spuDTO.forEach(item -> cartDTOS.forEach(item1 -> {
                    if (item1.getSkuId().equals(item.getSkuList().get(0).getId())) {
                        item1.setSpuDTO(item);
                    }
                }));
            }
            return toAjax(cartDTOS);
        }
        return error("查询失败");
    }

    @Override
    public AjaxResult add(Long accountId, Long skuId, Integer num) {
        if (num <= 0)
            num = 1;
        SpuDTO spuDTO = goodsFeignService.getBySkuIds(skuId);
        if (spuDTO == null || spuDTO.getStatus() != SpuStatusType.SELLING.getCode())
            return error("商品不存在/或已下架");

        if (spuDTO.getStock() <= num)
            num = spuDTO.getStock();

        CartDO cartDO = new CartDO();
        cartDO.setAccountId(accountId);
        cartDO.setSkuId(skuId);

        CartDO old = cartMapper.selectOne(cartDO);
        int count;
        if (old == null) {
            cartDO.setNum(num);
            count = cartMapper.insert(cartDO);

        } else {
            old.setNum(old.getNum() + num);
            count = cartMapper.updateById(old);
        }
        return toAjax(count);
    }

    @Override
    public AjaxResult del(Long accountId, Long skuId) {
        CartDO cartDO = new CartDO();
        cartDO.setAccountId(accountId);
        cartDO.setSkuId(skuId);

        cartDO = cartMapper.selectOne(cartDO);
        if (cartDO == null)
            return error("商品不存在！");

        int count = cartMapper.delete(new EntityWrapper<CartDO>().eq("sku_id", skuId).eq("account_id", accountId));
        return toAjax(count);
    }
}
