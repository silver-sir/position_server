package com.tct.positionApp.service;


import com.tct.positionApp.domain.Fences;
import com.tct.positionApp.domain.Parents;


public interface FencesService {
    /**
     *  电子围栏接口
     **/
    //querystatus	查询监控对象在围栏内或外
    public String queryStatus(double longitude,double latitude,int student_id);

    //querystatusbylocation	查询坐标在围栏内或外
    // unknown：未知状态
    //in：在围栏内
    //out：在围栏外
    public String queryStatusByLocation(double longitude,double latitude,int fence_id);

    //创建围栏
    public int createCircleFence(Fences fence);

    //更新围栏状态
    public void setUpdateStatus(int status, int fence_id);

    //更新围栏状态通过学生id
    public void setUpdateStatusByStudentId(int status, int student_id);

    //最后新增的围栏
    public Fences listByStudentId(int student_id);

    //小程序判断围栏
    public void templateSend(Parents parent_id);

    //historyalarm	查询某监控对象的历史报警信息
    //batchhistoryalarm	批量查询某 service 下时间段以内的所有报警信息，用于服务端报警同步
}
