package com.dlyy.api.web.vo;

public class RestResult {

    private int code = 0;

    private String msg;

    private Object data;

    public RestResult() {
    }

    public RestResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RestResult createSuccessResult(String msg) {
        return new RestResult(0, msg, null);
    }

    public static RestResult createErrorResult(String msg) {
        return new RestResult(0, msg, null);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

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
