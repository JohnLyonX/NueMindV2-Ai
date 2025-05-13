package com.nue.teamnus.service;

import com.nue.teamnus.pojo.EduTeachers;

import java.util.List;

public interface ITeachersService {
    List<EduTeachers> findAllTeachers();
    EduTeachers findTeacherById(int id);
}
