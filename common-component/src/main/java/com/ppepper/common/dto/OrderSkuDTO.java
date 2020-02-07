package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderSkuDTO extends SuperDTO {

    private Long skuId;

    private Long spuId;

    private Long orderId;

    private String orderNo;

    private String spuTitle;

    private String title;

    private String barCode;

    private Integer num;

    private Integer originalPrice;

    private Integer price;

    private String img;

    private String unit;

    private SpuDTO spuDTO;

}
