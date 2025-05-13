package com.nue.teamnus.service.Impl;

import com.nue.teamnus.mapper.EduStudentMapper;
import com.nue.teamnus.pojo.EduStudent;
import com.nue.teamnus.service.IEduStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EduStudentServiceImpl implements IEduStudentService {

    @Autowired
    private EduStudentMapper studentMapper;

    @Override
    public List<EduStudent> getAllStudents() {
        return studentMapper.selectAll();
    }

    @Override
    public EduStudent getStudentById(Long id) {
        return studentMapper.selectById(id);
    }

    @Override
    public boolean saveStudent(EduStudent student) {
        return studentMapper.insert(student) > 0;
    }

    @Override
    public boolean updateStudent(EduStudent student) {
        return studentMapper.update(student) > 0;
    }

    @Override
    public boolean deleteStudent(Long id) {
        return studentMapper.deleteById(id) > 0;
    }

    @Override
    public List<EduStudent> getStudentsByName(String name) {
        return studentMapper.selectByName(name);
    }
}
