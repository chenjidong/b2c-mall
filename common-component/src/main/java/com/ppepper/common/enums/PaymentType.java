package com.ppepper.common.enums;

/**
 * Created with ChenJiDong
 * Created By 2020-02-13
 */
public enum PaymentType {
    WECHAT(0, "微信支付"),
    ALIPAY(1, "阿里支付"),
    OFFLINE(2, "线下支付");

    PaymentType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
