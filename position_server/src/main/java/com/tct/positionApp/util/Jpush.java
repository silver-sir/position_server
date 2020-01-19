package com.tct.positionApp.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class Jpush {
    // 设置好账号的app_key和masterSecret
    public static String appKey = "83c3cd5945e18f9a83d96094";
    public static String masterSecret = "e20f5390d5f0c703a4e1a9b1";
    public static JPushClient jpushClient = new JPushClient(masterSecret, appKey);

    public static void jpushAndroid(String registrationId) {
        //推送的关键,构造一个payload
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                .setAudience(Audience.registrationId(registrationId))//你项目中的所有用户
                //发送内容
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                //这里是指定开发环境,不用设置也没关系
                .setMessage(Message.content("已出围栏范围"))//自定义信息
                .build();
        //.setNotification(Notification.android("电子围栏警告", "已出围栏范围", null))

        try {
            PushResult pu = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    public static void jpushAndroidMessage(String registrationId,String message) {
        //推送的关键,构造一个payload
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                .setAudience(Audience.registrationId(registrationId))//你项目中的所有用户
                //发送内容
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                //这里是指定开发环境,不用设置也没关系
                .setMessage(Message.content(message))//自定义信息
                .build();

        try {
            PushResult pu = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

}
