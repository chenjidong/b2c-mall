package com.ppepper.account.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ppepper.common.domain.SuperDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("b2c_address")
public class AddressDO extends SuperDO {

    private String province;
    private String city;
    private String county;
    private String address;
    @TableField("default_address")
    private Boolean defaultAddress;
    @TableField("account_id")
    private Long accountId;
    private String phone;
    private String consignee;


}
