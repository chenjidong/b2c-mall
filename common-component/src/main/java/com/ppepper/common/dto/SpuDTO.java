package com.ppepper.common.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SpuDTO extends SuperDTO {

    private List<SpuSkuDTO> skuList;

    private Integer originalPrice;

    private Integer price;

    private Integer vipPrice;

    private Integer stock;

    private Integer sales;

    private String title;

    /**
     * 主图
     */
    private String img;

    /**
     * 后面的图，仅在详情接口才出现
     */
    private List<String> imgList;

    private String detail;

    private String description;

    private Long categoryId;

    private List<Long> categoryIds;

    private String unit;

    private Long freightTemplateId;

    private Boolean collect;

    private Integer status;

    private Long shopId;
}
