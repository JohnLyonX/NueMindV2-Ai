package com.nue.teamnus.tool;

import com.nue.teamnus.pojo.EduCourses;
import com.nue.teamnus.service.IEduCoursesService;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SaleAgentTools {
    @Autowired
    private IEduCoursesService eduCoursesService;



    @Tool("""
            此工具返回目前拥有的课程
            适用于用户咨询|了解我们的课程
            """)
    public List<EduCourses> queryAllCourses() {
        return eduCoursesService.getAll();
    }

     @Tool("""
           此工具查询现有的教师
            """)
    public List<String> queryTeachers() {
         ArrayList<String> list = new ArrayList<>();
         list.add("梁工");
         list.add("刘工");
         list.add("韩工");
         return list;
    }

    @Tool("""
           查询我们的师资能力
           """)
    public List<String> queryTea(){
        ArrayList<String> list = new ArrayList<>();
        list.add("梁工 全站开发者，智能体开发者，主导过多个校园AI项目，擅长将创新想法转化为可行解决方案。");
        list.add("刘工 前端开发工程师，界面设计小能手，熟练React/Vue框架，追求极致的用户体验与交互逻辑");
        list.add("韩工 后端开发后端开发工程师，系统架构达人，在多个课程项目中搭建稳定服务，擅长数据库优化与接口设计。。");
        return list;
    }

}
