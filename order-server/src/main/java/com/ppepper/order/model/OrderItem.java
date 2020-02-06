package com.ppepper.order.model;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public class OrderItem {
    private Long itemId;
    private Long orderId;
    private Integer goodsCount;
    private GoodsDTO goodsDTO;

    public OrderItem(Long itemId, Long orderId, Integer goodsCount, GoodsDTO goodsDTO) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.goodsCount = goodsCount;
        this.goodsDTO = goodsDTO;
    }

    public GoodsDTO getGoodsDTO() {
        return goodsDTO;
    }

    public void setGoodsDTO(GoodsDTO goodsDTO) {
        this.goodsDTO = goodsDTO;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }
}
