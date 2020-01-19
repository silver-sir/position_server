package com.tct.positionApp.common.exception;

/**
 * Created by zhengping.zhu
 * on 2017/5/7.
 * 错误枚举类
 */
public enum ExceptionEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    USER_NOT_FIND(3003,"用户不存在"),
    USER_IS_EXIST(3005,"用户已存在"),
    PARAMETER_IS_NULL(3006,"参数为空"),
;

    private Integer code;

    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
