package com.nue.teamnus.mapper;

import com.nue.teamnus.pojo.EduExamFilelib;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EduExamFilelibMapper {
    // 新增查询方法
    List<EduExamFilelib> selectExamResults();

    int updateById(EduExamFilelib entity);
}
