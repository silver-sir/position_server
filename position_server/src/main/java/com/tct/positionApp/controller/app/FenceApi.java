package com.tct.positionApp.controller.app;

import com.tct.positionApp.common.exception.ExceptionHandle;
import com.tct.positionApp.common.exception.ResultUtil;
import com.tct.positionApp.domain.Fences;
import com.tct.positionApp.domain.Result;
import com.tct.positionApp.domain.Students;
import com.tct.positionApp.service.FencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/fence")
public class FenceApi {

    @Autowired
    private ExceptionHandle exceptionHandle;

    @Resource
    FencesService fencesService;


    @RequestMapping("/queryStatusByLocation")
    public Result<String> queryStatusByLocation(@RequestBody Map<String,Object> map){
        double longitude =  Double.parseDouble(map.get("longitude").toString());
        double latitude = Double.parseDouble(map.get("latitude").toString());
        int fence_id = Integer.parseInt(map.get("fence_id").toString());
        try{
            return ResultUtil.success(fencesService.queryStatusByLocation(longitude,latitude,fence_id));
        }catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }
    }

    @RequestMapping("/queryStatus")
    public Result<String> queryStatus(@RequestBody Map<String,Object> map){
        double longitude =  Double.parseDouble(map.get("longitude").toString());
        double latitude = Double.parseDouble(map.get("latitude").toString());
        int student_id = Integer.parseInt(map.get("student_id").toString());
        try{
            return ResultUtil.success(fencesService.queryStatus(longitude,latitude,student_id));
        }catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }
    }

    @RequestMapping("/createCircleFence")
    public Result<Integer> createCircleFence(@RequestBody Map<String,Object> map){
        Fences fence = new Fences();
        Students student = new Students();
        student.setId(Integer.parseInt(map.get("student_id").toString()));
        fence.setRadius(Double.parseDouble(map.get("radius").toString()));
        fence.setLongitude(Double.parseDouble(map.get("longitude").toString()));
        fence.setLatitude(Double.parseDouble(map.get("latitude").toString()));
        fence.setFence_name(map.get("fence_name").toString());
        fence.setStudent(student);
        try{
            return ResultUtil.success(fencesService.createCircleFence(fence));
        }catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }
    }

    @RequestMapping("/updateStatus")
    public void updateStatus(@RequestBody Map<String,Object> map){
        int status_id = Integer.parseInt(map.get("status").toString());
        int fence_id = Integer.parseInt(map.get("fence_id").toString());
        fencesService.setUpdateStatus(status_id,fence_id);
    }

    @RequestMapping("/fenceList/{student_id}")
    public Result<Fences> fenceList(@PathVariable("student_id") int student_id){
        try{
            return ResultUtil.success(fencesService.listByStudentId(student_id));
        }catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }
    }

}
