package com.ppepper.common.enums;

/**
 * Created with ChenJiDong
 * Created By 2020-02-13
 */
public enum PaymentStatusType {
    UNPAY(0, "未支付"),
    PAYING(1, "支付中"),
    SUCCESS(2, "支付完成"),
    REFUNDING(3, "退款中"),
    REFUND(4, "退款完成"),
    CANCEL(5, "取消");

    PaymentStatusType(int code, String msg) {
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
