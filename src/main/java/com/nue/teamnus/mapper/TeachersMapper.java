package com.nue.teamnus.mapper;

import com.nue.teamnus.pojo.EduTeachers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeachersMapper {
    List<EduTeachers> selectAllTeachers();
    EduTeachers selectTeachersById(@Param("id") int id);
}
