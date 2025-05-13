package com.nue.teamnus.mapper;

import com.nue.teamnus.pojo.EduCourses;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EduCoursesMapper {
    List<EduCourses> selectAll();

    List<EduCourses> selectByName(String name);
}
