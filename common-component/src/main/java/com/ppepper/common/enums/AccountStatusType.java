package com.ppepper.common.enums;

/**
 * Created with ChenJiDong
 * Created By 2020-02-13
 */
public enum  AccountStatusType {
    ENABLE(1,"正常"),
    DISABLE(2,"冻结");


    AccountStatusType(int code, String msg) {
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
