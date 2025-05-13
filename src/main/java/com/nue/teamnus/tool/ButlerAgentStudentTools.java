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
public class ButlerAgentStudentTools {

    @Autowired
    private IEduStudentService iEduStudentService;
    @Autowired
    private IEduCoursesService iEduCoursesService;

    @Autowired
    private IEduExamFilelibService eduExamFilelibService;


    @Tool("获取考试结果列表（包含编号、学生姓名、考试名称和分数）")
    public List<EduExamFilelib> getExamResults() {
        return eduExamFilelibService.getExamResults();
    }

}
