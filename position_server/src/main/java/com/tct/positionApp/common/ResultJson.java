//package com.tct.positionApp.common;
//
//import java.io.Serializable;
//
//public class ResultJson implements Serializable {
//    private static final long serialVersionUID = 1L;
//    // 状态码 正确为0
//    private String code = "0";
//    // 错误描述
//    private String msg = "成功";
//    // 返回对象
//    private Object data = "";
//
//    public ResultJson() {
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//
//    public ResultJson(String code) {
//        this.code = code;
//    }
//
//    public ResultJson(String code, String msg) {
//        this.code = code;
//        this.msg = msg;
//    }
//
//    public ResultJson(Object data) {
//        this.data = data;
//    }
//
//    public ResultJson(String code, String msg, Object data) {
//        this.code = code;
//        this.msg = msg;
//        this.data = data;
//    }
//}
