package com.ppepper.common.dto;


import lombok.Data;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Data
public class SkuDTO extends SuperDTO {

    private Long spuId;

    private String barCode;

    private Long categoryId;

    /**
     * SKU显示文字
     */
    private String title;

    private String spuTitle;

    private String img;

    private String spuImg;

    private Integer originalPrice;

    private Integer price;

    private Integer vipPrice;

    private Integer stock;

    private Integer freezeStock;

    private String unit;

}