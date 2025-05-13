package com.nue.teamnus.service.Impl;

import com.nue.teamnus.pojo.EduCourses;
import com.nue.teamnus.mapper.CoursesMapper;
import com.nue.teamnus.service.ICoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesServiceImpl implements ICoursesService {
    @Autowired
    private CoursesMapper coursesMapper;

    @Override
    public List<EduCourses> findAllCourses() {
        return coursesMapper.selectAll();
    }
}
