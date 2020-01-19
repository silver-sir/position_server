package com.tct.positionApp.controller.app;


import com.tct.positionApp.common.exception.ExceptionEnum;
import com.tct.positionApp.common.exception.ExceptionHandle;
import com.tct.positionApp.common.exception.ResultUtil;
import com.tct.positionApp.domain.Parents;
import com.tct.positionApp.domain.Result;
import com.tct.positionApp.domain.Students;
import com.tct.positionApp.service.ParentsService;
import com.tct.positionApp.service.StudentsService;
import com.tct.positionApp.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginIn {

    @Autowired
    private ExceptionHandle exceptionHandle;

    private ObjectUtil objectUtil = new ObjectUtil();

    @Resource
    ParentsService parentsService;

    @Resource
    StudentsService studentsService;

    @RequestMapping("/test/{username}/{p}")
    public Result<Parents> test(@PathVariable("username") String user, @PathVariable("p") String p){
        try{
            if (objectUtil.isNotEmpty(parentsService.findByUserName(user,p))){
               return ResultUtil.success(parentsService.findByUserName(user,p));
            }else{
                return ResultUtil.error(ExceptionEnum.USER_NOT_FIND);
            }
        } catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }
    }

    @RequestMapping("/test")
    public void test(){
        //return "aaa";
    }

    @RequestMapping("/student")
    public Result<Students> loginInStudent(@RequestBody Map<String,Object> map){
        try{
            Students student = studentsService.findByUserName(map.get("username").toString(),map.get("password").toString());
            if (objectUtil.isNotEmpty(student)){
                return ResultUtil.success(student);
            }else{
                return ResultUtil.error(ExceptionEnum.USER_NOT_FIND);
            }
        } catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }
    }

    @RequestMapping("/parent")
    public Result<Parents> loginInParent(@RequestBody Map<String,Object> map){
        try{
            Parents parent = parentsService.findByUserName(map.get("cellphone").toString(),map.get("password").toString());
            if (objectUtil.isNotEmpty(parent)){
                return ResultUtil.success(parent);
            }else{
                return ResultUtil.error(ExceptionEnum.USER_NOT_FIND);
            }
        } catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }
    }

    @RequestMapping("/setRegId/parent")
    public void setRegIdParent(@RequestBody Map<String,Object> map){
        String regId = map.get("regId").toString();
        int parent_id = Integer.parseInt(map.get("parent_id").toString());
        System.out.println("regId" + regId);

        parentsService.setUpdateRegIdParent(regId,parent_id);
        //return parentsDao.findByUserName(username).toString();
    }

    @RequestMapping("/setRegId/student")
    public void setRegIdStudent(@RequestBody Map<String,Object> map){
        String regId = map.get("regId").toString();
        int student_id = Integer.parseInt(map.get("student_id").toString());
        System.out.println("regId" + regId);
        studentsService.setUpdateRegId(regId,student_id);
    }

    @RequestMapping("/register")
    public Result<Integer> insertParent(@RequestBody Map<String,Object> map){
        Parents parents = new Parents();
        parents.setUserName("默认");
        int parentId = 0;

        try{
            if (objectUtil.isNotEmpty(map.get("cellphone").toString())&&objectUtil.isNotEmpty(map.get("password").toString())){
                parents.setCellPhone(map.get("cellphone").toString());
                parents.setPassword(map.get("password").toString());
                parentId = parentsService.insertParent(parents);
                if(parentId!=-1){
                    return ResultUtil.success(parentId);
                }else{
                    return ResultUtil.error(ExceptionEnum.USER_IS_EXIST);
                }

            }else{
                return ResultUtil.error(ExceptionEnum.PARAMETER_IS_NULL);
            }
        } catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }

    }

    @RequestMapping("/bind")
    public Result<Integer> bindStudent(@RequestBody Map<String,Object> map){
        int studentId=0;
        try{
            if (objectUtil.isNotEmpty(map.get("username").toString())&&objectUtil.isNotEmpty(map.get("password").toString())){
                studentId =  parentsService.bindStudent(map.get("username").toString(),map.get("password").toString(),Integer.parseInt(map.get("parent_id").toString()));
                if(studentId!=-1){
                    return ResultUtil.success(studentId);
                }else{
                    return ResultUtil.error(ExceptionEnum.USER_IS_EXIST);
                }
            }else{
                return ResultUtil.error(ExceptionEnum.PARAMETER_IS_NULL);
            }
        } catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }
    }

    @RequestMapping("/findStudent/{parent_id}")
    public Result<List<Students>> findStudentsByParentId(@PathVariable("parent_id") int parent_id){
        try{
            return ResultUtil.success(studentsService.findStudentsByParentId(parent_id));
        }catch (Exception e){
            return exceptionHandle.exceptionGet(e);
        }

    }
}
