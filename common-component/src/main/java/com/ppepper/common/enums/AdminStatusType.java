package com.ppepper.common.enums;

/**
 * Created with ChenJiDong
 * Created By 2020-02-13
 */
public enum AdminStatusType {
    LOCK(0, "冻结"),
    ACTIVE(1, "激活");


    private int code;

    private String msg;

    AdminStatusType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }


}
