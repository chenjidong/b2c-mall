package com.ppepper.goods.service;


import com.ppepper.goods.model.Goods;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Service
public class GoodsService {

    public static final Map<Long, Goods> goodsMap = new HashMap<Long, Goods>();

    static {
        goodsMap.put(1L, new Goods(1L, "商品01", "http://127.0.0.1/01.png", "商品01描述", 10.0F));
        goodsMap.put(2L, new Goods(2L, "商品02", "http://127.0.0.1/02.png", "商品02描述", 10.0F));
        goodsMap.put(3L, new Goods(3L, "商品03", "http://127.0.0.1/03.png", "商品03描述", 10.0F));
        goodsMap.put(4L, new Goods(4L, "商品04", "http://127.0.0.1/04.png", "商品04描述", 10.0F));
        goodsMap.put(5L, new Goods(5L, "商品05", "http://127.0.0.1/05.png", "商品05描述", 10.0F));
        goodsMap.put(6L, new Goods(6L, "商品06", "http://127.0.0.1/06.png", "商品06描述", 10.0F));
        goodsMap.put(7L, new Goods(7L, "商品07", "http://127.0.0.1/07.png", "商品07描述", 10.0F));
        goodsMap.put(8L, new Goods(8L, "商品08", "http://127.0.0.1/08.png", "商品08描述", 10.0F));
        goodsMap.put(9L, new Goods(9L, "商品09", "http://127.0.0.1/09.png", "商品09描述", 10.0F));
        goodsMap.put(10L, new Goods(10L, "商品10", "http://127.0.0.1/10.png", "商品10描述", 10.0F));
    }

    public Goods findGoodsById(Long id) {
        return goodsMap.get(id);
    }
}
