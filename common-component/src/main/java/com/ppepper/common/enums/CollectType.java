package com.ppepper.common.enums;

/**
 * Created with ChenJiDong
 * Created By 2020-02-08
 */
public enum CollectType {
    SPU(0, "商品"),
    SHOP(1, "店铺"),;

    CollectType(int code, String msg) {
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
