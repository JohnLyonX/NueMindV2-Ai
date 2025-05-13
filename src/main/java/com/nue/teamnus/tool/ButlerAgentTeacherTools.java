package com.nue.teamnus.tool;

import com.nue.teamnus.pojo.EduCourses;
import com.nue.teamnus.pojo.EduExamFilelib;
import com.nue.teamnus.pojo.EduStudent;
import com.nue.teamnus.service.IEduCoursesService;
import com.nue.teamnus.service.IEduExamFilelibService;
import com.nue.teamnus.service.IEduStudentService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ButlerAgentTeacherTools {

    @Autowired
    private IEduStudentService iEduStudentService;
    @Autowired
    private IEduCoursesService iEduCoursesService;

    @Autowired
    private IEduExamFilelibService eduExamFilelibService;


    @Tool("""
            此工具返回目前拥有的课程
            适用于用户咨询|了解我们的课程
            """)
    public List<EduCourses> queryAllCourses() {
        return iEduCoursesService.getAll();
    }

    @Tool("""
            查询所有学生
            """)
    public List<EduStudent> getAllStudents() {
        return iEduStudentService.getAllStudents();
    }

    @Tool("""
            删除指定学生
            :param studentId: 要删除的学生ID
            """)
    public boolean deleteStudent(@P("传入学生ID") Long studentId) {
        return iEduStudentService.deleteStudent(studentId);
    }

    @Tool("""
            添加新学生
            :param name: 学生姓名
            :param phoneNumber: 手机号码
            :param password: 登录密码
            :param avatarUrl: 头像URL (可选)
            """)
    public boolean addStudent(String name, String phoneNumber, String password,
                              @P("传入null") String avatarUrl) {
        EduStudent student = new EduStudent();
        student.setName(name);
        student.setPhoneNumber(phoneNumber);
        student.setPassword(password);
        student.setUrl(avatarUrl);
        return iEduStudentService.saveStudent(student);
    }

    @Tool("""
            修改学生信息
            :param studentId: 学生ID
            :param name: 新姓名 (可选)
            :param phoneNumber: 新手机号 (可选)
            :param avatarUrl: 新头像URL (可选)
            """)
    public boolean updateStudent(Long studentId,
                                 @P("姓名") String name,
                                 @P("手机号码") String phoneNumber,
                                 @P("密码") String password
                                 ) {
        EduStudent student = iEduStudentService.getStudentById(studentId);
        if (student == null) return false;

        if (name != null) student.setName(name);
        if (phoneNumber != null) student.setPhoneNumber(phoneNumber);
        if (password != null) student.setPassword(password);
        return iEduStudentService.updateStudent(student);
    }

    @Tool("获取考试结果列表（包含编号、学生姓名、考试名称和分数）")
    public List<EduExamFilelib> getExamResults() {
        return eduExamFilelibService.getExamResults();
    }

}
