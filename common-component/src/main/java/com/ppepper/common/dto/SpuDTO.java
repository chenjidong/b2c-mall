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

//    private List<CategoryDTO> categoryList;
//
//    private List<SpuAttributeDO> attributeList;

    /**
     * 商品的第一页(前10条)评价
     */
//    private Page<AppraiseResponseDTO> appraisePage;

    /**
     * 商品现在携带的团购信息
     */
//    private GroupShopDTO groupShop;

    private String unit;

    private Long freightTemplateId;

//    private FreightTemplateDTO freightTemplate;

    private Boolean collect;

    private Integer status;

}
