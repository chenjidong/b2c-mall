package com.ppepper.common.enums;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
public enum CouponType {
    COMMON(1, "普通券"),
    CDKEY(2, "兑换券"),
    ;

    CouponType(int code, String msg) {
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
