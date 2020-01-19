///*
//package com.tct.positionApp.common;
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//@ControllerAdvice
//public class RestControllerResponseAdvice implements ResponseBodyAdvice<Object> {
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return true;
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
//                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
//                                  ServerHttpResponse response) {
//        // 对body进行封装处理
//        if (body instanceof String) {
//            String msg = (String) body;
//            ResultJson resultJson = new ResultJson("200", msg);
//            // 因为在controller层中返回的是String类型，这边如果换成ResultJson的话，会导致StringMessageConverter方法类型转换异常，所以这边将对象转成字符串
//            return JSONObject.toJSONString(resultJson);
//        } else if (body instanceof Object){
//            Object data = (Object) body;
//            ResultJson resultJson = new ResultJson(data);
//
//            return resultJson;
//        }else{
//            ResultJson resultJson = new ResultJson("400", "error");
//            // 因为在controller层中返回的是String类型，这边如果换成ResultJson的话，会导致StringMessageConverter方法类型转换异常，所以这边将对象转成字符串
//            return JSONObject.toJSONString(resultJson);
//        }
//    }
//}
//*/
