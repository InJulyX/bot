package com.bot.entity;

import lombok.Builder;

@Builder
public class BaseResult<T> {
    public static final int success = 200;
    public static final int fail = -1;
    private int code;
    private String message;
    private T data;

    public static int getSuccess() {
        return success;
    }

    public static int getFail() {
        return fail;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

}
