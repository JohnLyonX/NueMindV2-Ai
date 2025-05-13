package com.nue.teamnus.service.Impl;

import com.nue.teamnus.mapper.TeachersMapper;
import com.nue.teamnus.pojo.EduTeachers;
import com.nue.teamnus.service.ITeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachersServiceImpl implements ITeachersService {
    @Autowired
    private TeachersMapper teachersMapper;
    @Override
    public List<EduTeachers> findAllTeachers() {
        return teachersMapper.selectAllTeachers();
    }

    @Override
    public EduTeachers findTeacherById(int id) {
        return teachersMapper.selectTeachersById(id);
    }
}
