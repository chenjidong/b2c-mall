package com.ppepper.order.model;

import java.util.Date;
import java.util.List;
/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public class Order {
    private Long orderId;
    private Long userId;
    private Date createDate;
    private Date updateDate;
    private List<OrderItem> items;

    public Order(Long orderId, Long userId, Date createDate, Date updateDate, List<OrderItem> items) {
        this.orderId = orderId;
        this.userId = userId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
