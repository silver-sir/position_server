package com.tct.positionApp.service;

import com.tct.positionApp.domain.Parents;
import com.tct.positionApp.domain.Students;

public interface ParentsService {
    void setUpdateRegIdParent(String regId,int parent_id);

    void setUpdateFormIdParent(String form_id,int parent_id);

    void setUpdateOpenIdParent(String open_id,int parent_id);

    Parents findByStudentId(int student_id);

    void setUpdateStatusParent(int status,int parent_id);

    Parents findByUserName(String cellphone,String passWord);

    int insertParent(Parents parent);

    int bindStudent(String username,String password,int parent_id);
}
