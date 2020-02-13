package com.ppepper.payment.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with ChenJiDong
 * Created By 2020-02-13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "b2c_payment")
public class PaymentDO extends SuperDO {

    private String channel;//支付来源
    private Integer type;//支付类型
    @TableField(value = "pay_no")
    private String payNo;//第三方支付单号 线下支付时  该字段为 后台管理员上传的 凭据路径

    @TableField(value = "order_no")
    private String orderNo;

    private Integer amount;//支付金额
    private Integer status;//支付状态

    @TableField(value = "account_id")
    private Long accountId;
}
