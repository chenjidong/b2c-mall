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
public class SpuAppraiseDTO extends SuperDTO {

    private String content;

    private Integer score;

    private List<String> imgList;

    private Long accountId;

    private String userNickName;

    private String userAvatarUrl;

    private Long orderId;

    private Long spuId;

    private String spuTitle;

    private Long skuId;

    private String skuTitle;

}
