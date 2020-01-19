package com.tct.positionApp.dao;

import com.tct.positionApp.domain.Fences;
import com.tct.positionApp.domain.Status;
import org.apache.ibatis.annotations.*;


public interface FencesDao {
    /**
     *  管理围栏属性
     **/
    //createcirclefence	创建圆形围栏
    @Insert("INSERT INTO fences(fence_name,longitude,latitude,radius,student_id,createtime,status) VALUES(#{fence_name},#{longitude},#{latitude},#{radius},#{student.id},#{createTime},#{status.id})")
    @SelectKey(statement="select LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    void insert(Fences fence);

//    @Update("UPDATE fences SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
//    public void update(Fences fence);

    //delete	删除围栏
    @Delete("DELETE FROM fences WHERE id =#{id}")
    void delete(int id);
    //list	查询围栏信息
    @Select("SELECT * FROM fences WHERE id = #{id}")
    Fences list(@Param("id") int id);

    @Select("SELECT * FROM fences " +
            "f WHERE student_id = #{student_id} ORDER by f.createtime DESC LIMIT 1 ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "status", javaType = Status.class, column = "status", many = @Many(select = "com.tct.positionApp.dao.StatusDao.findStatusById") )
    })
    Fences listByStudentId(@Param("student_id") int student_id);

    @Select("SELECT reg_id FROM  parents p WHERE id=(SELECT parent_id from students WHERE id = #{student_id})")
    String regIdByStudentId(@Param("student_id") int student_id);

    @Select("SELECT reg_id FROM  parents p WHERE id=(SELECT parent_id from students WHERE id =(SELECT student_id from fences WHERE id = #{fence_id}))")
    String regIdByFenceId(@Param("fence_id") int fence_id);

    @Select("SELECT reg_id FROM students WHERE id=(SELECT student_id FROM fences WHERE id=#{fence_id})")
    String studentRegIdByFenceId(@Param("fence_id") int fence_id);

    @Update("UPDATE fences set status=#{status} where id=#{fence_id}")
    void setUpdateStatus(@Param("status") int status, @Param("fence_id") int fence_id);

    /**
     *  管理围栏监控对象
     **/
    //addmonitoredperson 增加围栏需监控的entity
    //deletemonitoredperson 删除围栏可去除监控的entity
    //listmonitoredperson 查询围栏监控的所有entity


}
