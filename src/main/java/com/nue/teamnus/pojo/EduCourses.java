package com.nue.teamnus.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EduCourses {
    /*课程ID*/
    private int id;
    /*课程名称*/
    private String name;
    /*课程图片*/
    private String url;
    /*课程描述*/
    private String summary;
    /*价格*/
    private float price;
    /*状态*/
    private String status;
    /*创建时间*/
    private LocalDateTime createTime;
    /*更新时间*/
    private LocalDateTime updateTime;
}
