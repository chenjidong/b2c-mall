package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with ChenJiDong
 * Created By 2020-02-13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentDTO extends SuperDTO {

    private String channel;//支付来源
    private Integer type;//支付类型
    private String payNo;//第三方支付单号
    private String orderNo;//订单号
    private Integer amount;//支付金额
    private Integer status;//支付状态
    private Long accountId;
}
