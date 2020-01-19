package com.tct.positionApp.dao;

import com.tct.positionApp.domain.Parents;
import com.tct.positionApp.domain.Students;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentsDao {
    @Update("UPDATE students set reg_id=#{regId} where id=#{student_id}")
    void setUpdateRegId(@Param("regId") String regId, @Param("student_id") int student_id);

    @Select("SELECT * FROM students WHERE username = #{username} and password = #{password}")
    Students findByUserName(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM students WHERE username = #{username}")
    Students findByUserNameIsExist(@Param("username") String username);

    @Select("SELECT * FROM students WHERE parent_id = #{id}")
    List<Students> findStudentsByParentId(@Param("id") int id);

    @Insert("INSERT INTO students(username,password,parent_id) VALUES(#{userName},#{password},#{parent.id})")
    @SelectKey(statement="select LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    void insertStudent(Students student);
}
