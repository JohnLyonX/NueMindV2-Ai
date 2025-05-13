package com.nue.teamnus.pojo;

import lombok.Data;

@Data
public class EduExamFilelib {
    private Long id;
    private Long studentId;
    private String name;
    private String coursesName;
    private Long courseChapterId;
    private String coursesChapter;
    private String subPath;
    private String correctPath;
    private Long coursesId;
    private String coursesExamName;
    private int score;
}
