package com.nue.teamnus.pojo.vo;

import lombok.Data;

@Data
public class EduExamFilelibVo {
    private Long id;
    private Long studentId;
    private String name;
    private String coursesName;
    private Long coursesId;
    private String coursesExamName;
    private int score;
}
