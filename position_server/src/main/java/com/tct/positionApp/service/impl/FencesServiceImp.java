package com.tct.positionApp.service.impl;


import com.tct.positionApp.dao.FencesDao;
import com.tct.positionApp.domain.Fences;
import com.tct.positionApp.domain.Parents;
import com.tct.positionApp.domain.Status;
import com.tct.positionApp.service.FencesService;
import com.tct.positionApp.service.ParentsService;
import com.tct.positionApp.util.Jpush;
import com.tct.positionApp.util.MapUtil;
import com.tct.positionApp.util.MathUtil;
import com.tct.positionApp.wechat.WechatApi;
import com.tct.positionApp.wechat.WechatConf;
import com.tct.positionApp.wechat.WechatTemplate;
import com.tct.positionApp.wechat.WechatTemplateItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("FenceService")
public class FencesServiceImp implements FencesService {
    @Resource
    FencesDao fencesDao;

    @Resource
    ParentsService parentsService;

    Jpush jpush = new Jpush();

    MathUtil mathUtil = new MathUtil();

    /**
     *  电子围栏接口
     **/
    //querystatusbylocation	查询坐标在围栏内或外
    @Override
    public String queryStatusByLocation(double longitude, double latitude, int fence_id) {
        Fences fence = fencesDao.list(fence_id);
        double distance = mathUtil.distance(fence.getLongitude(),fence.getLatitude(),longitude,latitude);
        if(distance(distance,fence.getRadius()).equals("out")){
            jpush.jpushAndroid(fencesDao.regIdByFenceId(fence_id));
            return "out";
        }else{
            return distance(distance,fence.getRadius());
        }
    }

    //querystatus	查询监控对象在围栏内或外
    @Override
    public String queryStatus(double longitude, double latitude, int student_id) {
        Fences fence = fencesDao.listByStudentId(student_id);
        double distance = mathUtil.distance(fence.getLongitude(),fence.getLatitude(),longitude,latitude);
        if(distance(distance,fence.getRadius()).equals("out")){
            jpush.jpushAndroid(fencesDao.regIdByStudentId(student_id));
            if(parentsService.findByStudentId(student_id).getStatus().getId()== 3){
                templateSend(parentsService.findByStudentId(student_id));
            }
            return "out";
        }else{
            return distance(distance,fence.getRadius());
        }
    }

    @Override
    public int createCircleFence(Fences fence) {
        Status status = new Status();
        status.setId(2);
        fence.setStatus(status);
        fencesDao.insert(fence);
        return fence.getId();
    }

    @Override
    public void setUpdateStatus(int status, int fence_id) {
        try{
            fencesDao.setUpdateStatus(status,fence_id);
            jpush.jpushAndroidMessage(fencesDao.studentRegIdByFenceId(fence_id),status+"");
        }catch (Exception e){

        }

    }

    @Override
    public void setUpdateStatusByStudentId(int status, int student_id) {
        fencesDao.setUpdateStatus(status,fencesDao.listByStudentId(student_id).getId());
    }

    @Override
    public Fences listByStudentId(int student_id) {
        return  fencesDao.listByStudentId(student_id);
    }

    @Override
    public void templateSend(Parents parent) {
        String accessToken = WechatApi.getAccessToken();
        // 填充模板数据 （测试代码，写死）
        WechatTemplate wechatTemplate = new WechatTemplate()
                .setTouser(parent.getOpen_id())
                .setTemplate_id(WechatConf.templateId)
                // 表单提交场景下为formid，支付场景下为prepay_id
                .setForm_id(parent.getForm_id())
                // 跳转页面
                .setPage("pages/map/map")
                /**
                 * 模板内容填充：随机字符
                 * 购买地点 {{keyword1.DATA}}
                 * 购买时间 {{keyword2.DATA}}
                 * 物品名称 {{keyword3.DATA}}
                 * 交易单号 {{keyword4.DATA}}
                 * -> {"keyword1": {"value":"xxx"}, "keyword2": ...}
                 */
                .setData(MapUtil.newHashMap(//
                        "keyword1", new WechatTemplateItem("您的小孩已经离开安全范围"),//
                        "keyword2", new WechatTemplateItem("请查看孩子位置")
                ));
        parentsService.setUpdateStatusParent(4,parent.getId());
        WechatApi.templateSend(accessToken, wechatTemplate);
    }


    public String distance(double distance, double radius){
        if(distance > radius){
            return "out";
        }else if(distance <= radius){
            return "in";
        }else{
            return "unknown";
        }
    }
    //historyalarm	查询某监控对象的历史报警信息
    //batchhistoryalarm	批量查询某 service 下时间段以内的所有报警信息，用于服务端报警同步
}
