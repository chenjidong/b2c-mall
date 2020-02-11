package com.ppepper.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddressDTO extends SuperDTO {

    private String province;
    private String city;
    private String county;
    private String address;
    private Boolean defaultAddress;
    private Long accountId;
    private String phone;
    private String consignee;


}
