package com.tct.positionApp.service;

import com.tct.positionApp.domain.Students;

import java.util.List;

public interface StudentsService {
    void setUpdateRegId(String regId,int student_id);

    Students findByUserName(String userName,String passWord);

    List<Students> findStudentsByParentId(int parent_id);
}
