package com.nue.teamnus.service;

import com.nue.teamnus.pojo.EduCourses;

import java.util.List;

public interface ICoursesService {
    List<EduCourses> findAllCourses();
}
