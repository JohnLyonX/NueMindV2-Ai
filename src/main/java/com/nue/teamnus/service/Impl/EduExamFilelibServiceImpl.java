package com.nue.teamnus.service.Impl;

import com.nue.teamnus.mapper.EduExamFilelibMapper;
import com.nue.teamnus.pojo.EduExamFilelib;
import com.nue.teamnus.service.IEduExamFilelibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EduExamFilelibServiceImpl implements IEduExamFilelibService {
    @Autowired
    private EduExamFilelibMapper eduExamFilelibMapper;

    @Transactional
    @Override
    public boolean update(EduExamFilelib entity) {
        return eduExamFilelibMapper.updateById(entity) > 0;
    }

    @Override
    public List<EduExamFilelib> getExamResults() {
        return eduExamFilelibMapper.selectExamResults();
    }
}
