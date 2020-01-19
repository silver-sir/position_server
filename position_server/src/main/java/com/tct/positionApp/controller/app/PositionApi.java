package com.tct.positionApp.controller.app;

import com.tct.positionApp.common.exception.ExceptionHandle;
import com.tct.positionApp.common.exception.ResultUtil;
import com.tct.positionApp.domain.Positions;
import com.tct.positionApp.domain.Result;
import com.tct.positionApp.domain.Students;
import com.tct.positionApp.service.PositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/position")
public class PositionApi {

    @Autowired
    private ExceptionHandle exceptionHandle;

    @Resource
    PositionsService positionsService;

    @RequestMapping("/findAllPositions")
    public Result<List<Positions>> findAllPositions(@RequestBody Map<String,Object> map){
        try{
            return ResultUtil.success(positionsService.findAllPositions(Integer.parseInt(map.get("student_id").toString())));
        }catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }
    }

    @RequestMapping("/insertPosition")
    public Result<Integer> insertPosition(@RequestBody Map<String,Object> map){
        Students student = new Students();
        student.setId(Integer.parseInt(map.get("student_id").toString()));
        //SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Positions position = new Positions();
        position.setLatitude(Double.parseDouble(map.get("latitude").toString()));
        position.setLongitude(Double.parseDouble(map.get("longitude").toString()));
        position.setStudent(student);
        try{
            position.setUploadTime(sdf.parse(map.get("time").toString()));
            return ResultUtil.success(positionsService.insertPosition(position));
        }catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }
    }

    @RequestMapping("/findPositionsByDay")
    public Result<List<Positions>> findPositionsByDay(@RequestBody Map<String,Object> map){
        try{
            return ResultUtil.success(positionsService.findPositionsByDay(Integer.parseInt(map.get("student_id").toString()),map.get("time").toString()));
        }catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }
    }

    @RequestMapping("/findPosition")
    public Result<Positions> findPosition(@RequestBody Map<String,Object> map){
        try{
            return ResultUtil.success(positionsService.findPosition(Integer.parseInt(map.get("student_id").toString())));
        }catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }
    }
}
