package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDTO extends SuperDTO {

    private String channel;

    private String orderNo;

    private Long userId;

    private Integer status;

    private Integer skuOriginalTotalPrice;

    private Integer skuTotalPrice;

    private Integer freightPrice;

    private Integer couponPrice;

    private Long couponId;

    private Integer actualPrice;

    private Integer payPrice;

    private String payId;

    private String payChannel;

    private Date gmtPay;

    private String shipNo;

    private String shipCode;

    private String province;

    private String city;

    private String county;

    private String address;

    private String phone;

    private String consignee;

    private String mono;

    private Date gmtShip;

    private Date gmtConfirm;

    private List<OrderSkuDTO> skuList;

}
