package com.ppepper.common.enums;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
public enum CouponStatusType {
    LOCK(0, "冻结"),
    ACTIVE(1, "激活"),
    ;

    CouponStatusType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
