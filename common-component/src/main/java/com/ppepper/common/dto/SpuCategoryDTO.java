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
public class SpuCategoryDTO extends SuperDTO {

    private Long parentId;

    private String title;

    private String iconUrl;

    private String picUrl;

    private List<SpuCategoryDTO> childrenList;

}
