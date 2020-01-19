package com.tct.positionApp.dao;

import com.tct.positionApp.domain.Positions;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface PositionsDao {

    @Select("SELECT * FROM positions WHERE student_id = #{student_id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "uploadTime", column = "upload_time"),
            @Result(property = "createTime", column = "time"),
    })
    List<Positions> findAllPositions(@Param("student_id") int student_id);

    @Insert("INSERT INTO positions(latitude,longitude,time,student_id,upload_time) VALUES(#{latitude},#{longitude},#{createTime},#{student.id},#{uploadTime})")
    @SelectKey(statement="select LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    int insertPosition(Positions position);

    @Select("SELECT * FROM positions WHERE date(upload_time)=#{time} and student_id=#{student_id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "uploadTime", column = "upload_time"),
            @Result(property = "createTime", column = "time"),
    })
    List<Positions> findPositionsByDay(@Param("student_id") int student_id,@Param("time") String time);

    @Select("SELECT * FROM positions WHERE student_id = #{student_id} order by time desc limit 0,1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "uploadTime", column = "upload_time"),
            @Result(property = "createTime", column = "time"),
    })
    Positions findPosition(@Param("student_id") int student_id);
}
