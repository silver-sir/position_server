package com.tct.positionApp.dao;


import com.tct.positionApp.domain.Parents;
import com.tct.positionApp.domain.Status;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ParentsDao{

    @Select("SELECT * FROM parents WHERE cellphone = #{cellphone}")
    Parents findByCellPhone(@Param("cellphone") String cellphone);

    @Select("SELECT * FROM parents WHERE cellphone = #{cellphone} and password = #{password}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "students", javaType = List.class, column = "id", many = @Many(select = "com.tct.positionApp.dao.StudentsDao.findStudentsByParentId") )
    })
    Parents findByUserName(@Param("cellphone") String cellphone,@Param("password") String password);

    @Select("SELECT * FROM parents WHERE id=(SELECT parent_id FROM students WHERE id=#{student_id})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "status", javaType = Status.class, column = "status", many = @Many(select = "com.tct.positionApp.dao.StatusDao.findStatusById") )
    })
    Parents findByStudentId(@Param("student_id") int student_id);

    @Update("UPDATE parents set reg_id=#{regId} where id=#{parent_id}")
    void setUpdateRegIdParent(@Param("regId") String regId,@Param("parent_id") int parent_id);

    @Update("UPDATE parents set form_id=#{form_id},status='3' where id=#{parent_id}")
    void setUpdateFormIdParent(@Param("form_id") String form_id,@Param("parent_id") int parent_id);

    @Update("UPDATE parents set open_id=#{open_id} where id=#{parent_id}")
    void setUpdateOpenIdParent(@Param("open_id") String open_id,@Param("parent_id") int parent_id);

    @Update("UPDATE parents set status=#{status} where id=#{parent_id}")
    void setUpdateStatusParent(@Param("status") int status,@Param("parent_id") int parent_id);

    @Insert("INSERT INTO parents(username,password,cellphone) VALUES(#{userName},#{password},#{cellPhone})")
    @SelectKey(statement="select LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    void insert(Parents parent);
}
