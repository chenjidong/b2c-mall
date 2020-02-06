package com.ppepper.common.exception;

import java.io.Serializable;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
public abstract class ServiceException extends Exception implements Serializable {

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ServiceException() {
    }

    public ServiceException(String message, int code) {
        super(message);
        this.code = code;
    }
}
