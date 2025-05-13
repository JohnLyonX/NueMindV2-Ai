package com.nue.teamnus.service;

import com.nue.teamnus.pojo.EduStudent;
import java.util.List;

public interface IEduStudentService {
    List<EduStudent> getAllStudents();
    EduStudent getStudentById(Long id);
    List<EduStudent> getStudentsByName(String name);
    boolean saveStudent(EduStudent student);
    boolean updateStudent(EduStudent student);
    boolean deleteStudent(Long id);
}
