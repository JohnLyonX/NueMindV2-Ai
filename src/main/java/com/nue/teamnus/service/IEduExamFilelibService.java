package com.nue.teamnus.service;

import com.nue.teamnus.pojo.EduExamFilelib;

import java.util.List;

public interface IEduExamFilelibService {
    boolean update(EduExamFilelib entity);
     List<EduExamFilelib> getExamResults();
}
