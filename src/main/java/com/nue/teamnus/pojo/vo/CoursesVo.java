package com.nue.teamnus.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CoursesVo {
      /*课程ID*/
    private int id;
    /*课程名称*/
    private String name;
    /*课程描述*/
    private String summary;
    /*教师名字*/
    private String teacherName;
    /*教师描述*/
    private String teachersummary;
    /*教师职位*/
    private String teacherPosition;
    /*价格*/
    private float price;
    /*创建时间*/
    private LocalDateTime createTime;
}
