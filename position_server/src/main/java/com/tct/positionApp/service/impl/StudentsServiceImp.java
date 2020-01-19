package com.tct.positionApp.service.impl;

import com.tct.positionApp.dao.StudentsDao;
import com.tct.positionApp.domain.Students;
import com.tct.positionApp.service.StudentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("StudentsService")
public class StudentsServiceImp implements StudentsService {
    @Resource
    StudentsDao studentsDao;

    @Override
    public void setUpdateRegId(String regId, int student_id) {
        studentsDao.setUpdateRegId(regId, student_id);
    }

    @Override
    public Students findByUserName(String userName, String passWord) {
        return studentsDao.findByUserName(userName,passWord);
    }

    @Override
    public List<Students> findStudentsByParentId(int parent_id) {
        return studentsDao.findStudentsByParentId(parent_id);
    }


}
