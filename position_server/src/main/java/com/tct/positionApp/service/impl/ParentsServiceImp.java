package com.tct.positionApp.service.impl;

import com.tct.positionApp.dao.ParentsDao;
import com.tct.positionApp.dao.StudentsDao;
import com.tct.positionApp.domain.Parents;
import com.tct.positionApp.domain.Students;
import com.tct.positionApp.service.ParentsService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service("ParentsService")
public class ParentsServiceImp implements ParentsService {
    @Resource
    ParentsDao parentsDao;

    @Resource
    StudentsDao studentsDao;

    @Override
    public void setUpdateRegIdParent(String regId, int parent_id) {
        parentsDao.setUpdateRegIdParent(regId,parent_id);
    }

    @Override
    public void setUpdateFormIdParent(String form_id, int parent_id) {
        parentsDao.setUpdateFormIdParent(form_id,parent_id);
    }

    @Override
    public void setUpdateOpenIdParent(String open_id, int parent_id) {
        parentsDao.setUpdateOpenIdParent(open_id,parent_id);
    }

    @Override
    public Parents findByStudentId(int student_id) {
        return parentsDao.findByStudentId(student_id);
    }

    @Override
    public void setUpdateStatusParent(int status,int parent_id) {
        parentsDao.setUpdateStatusParent(status,parent_id);
    }

    @Override
    public Parents findByUserName(String cellphone, String passWord) {
        return parentsDao.findByUserName(cellphone,passWord);
    }

    @Override
    public int insertParent(Parents parent) {
        if(ObjectUtils.isEmpty(parentsDao.findByCellPhone(parent.getCellPhone()))){
            parentsDao.insert(parent);
            return parent.getId();
        }else{
            return -1;
        }

    }

    @Override
    public int bindStudent(String username,String password,int parent_id) {
        if(ObjectUtils.isEmpty(studentsDao.findByUserNameIsExist(username))){
            Parents parent = new Parents();
            parent.setId(parent_id);
            Students student = new Students();
            student.setUserName(username);
            student.setPassword(password);
            student.setParent(parent);
            studentsDao.insertStudent(student);
            return student.getId();
        }else{
            return -1;
        }

    }


}
