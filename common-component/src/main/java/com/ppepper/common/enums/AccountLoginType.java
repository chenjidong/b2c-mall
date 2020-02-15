package com.ppepper.common.enums;

/**
 * Created with ChenJiDong
 * Created By 2020-02-13
 */
public enum AccountLoginType {
    USER(0, "普通用户登录"),
    SHOP(1, "商铺用户");

    AccountLoginType(int code, String msg) {
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

    public static boolean contains(Integer loginType) {
        if (loginType == null) {
            return false;
        }
        for (AccountLoginType type : values()) {
            if (type.getCode() == loginType) {
                return true;
            }
        }
        return false;
    }


}
