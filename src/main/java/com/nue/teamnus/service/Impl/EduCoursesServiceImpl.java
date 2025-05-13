package com.nue.teamnus.service.Impl;

import com.nue.teamnus.pojo.EduCourses;
import com.nue.teamnus.mapper.EduCoursesMapper;
import com.nue.teamnus.service.IEduCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EduCoursesServiceImpl implements IEduCoursesService {

    @Autowired
    private EduCoursesMapper eduCoursesMapper;


    @Override
    public List<EduCourses> getAll() {
        return eduCoursesMapper.selectAll();
    }

    @Override
    public List<EduCourses> selectByName(String name) {
        return eduCoursesMapper.selectByName(name);
    }

}
