package com.nue.teamnus.service;

import com.nue.teamnus.pojo.EduCourses;
import com.nue.teamnus.pojo.EduStudent;

import java.util.List;

public interface IEduCoursesService {
    List<EduCourses> getAll();
    List<EduCourses> selectByName(String name);
}
