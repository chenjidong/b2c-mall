package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CartDTO extends SuperDTO {

    private Long skuId;

    private Integer num;

    private Long accountId;

    private SpuDTO spuDTO;

}
