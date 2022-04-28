package com.dlyy.api.web.vo;

public enum RestCode {

    SUCCESS(0, "请求成功"),

    FAILURE_PARAM_LACK(200001, "参数缺失"),

    FAILURE_PARAM_ERROR(200003, "非法参数"),

    FAILURE_CRYPT_ERROR(200004, "数据加/解密错误"),

    FAILURE_PARK_NOT_EXIST(200005, "停车场不存在"),

    FAILURE_OTHER(-1, "请求失败");

    int codeValue;

    String desp;

    RestCode(int codeValue, String desp) {
        this.codeValue = codeValue;
        this.desp = desp;
    }

    public int getCodeValue() {
        return codeValue;
    }

    public String getDesp() {
        return desp;
    }
}
