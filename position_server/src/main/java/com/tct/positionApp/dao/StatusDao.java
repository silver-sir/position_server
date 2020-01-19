package com.tct.positionApp.dao;

import com.tct.positionApp.domain.Parents;
import com.tct.positionApp.domain.Status;
import com.tct.positionApp.domain.Students;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StatusDao {
    @Select("SELECT * FROM status WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "statusName", column = "status_name"),
    })
    Status findStatusById(@Param("id") int id);
}
