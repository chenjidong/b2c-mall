package com.ppepper.common.enums;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public enum SpuStatusType {
    STOCK(0, "库存中"),
    SELLING(1, "售卖中"),
            ;

    SpuStatusType(int code, String msg) {
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
