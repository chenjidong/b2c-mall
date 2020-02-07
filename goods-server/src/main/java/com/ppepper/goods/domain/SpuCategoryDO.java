package com.ppepper.goods.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 * 商品sku 分类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("b2c_spu_category")
public class SpuCategoryDO extends SuperDO {

    private String title;

    @TableField("parent_id")
    private Long parentId;

    /**
     * 图标
     */
    @TableField("icon_url")
    private String iconUrl;

    /**
     * 分类图片
     */
    @TableField("pic_url")
    private String picUrl;

    private Integer level;

}
