package com.nue.teamnus.mapper;

import com.nue.teamnus.pojo.EduStudent;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EduStudentMapper {
    List<EduStudent> selectAll();
    List<EduStudent> selectByName(String name);
    EduStudent selectById(Long id);
    int insert(EduStudent student);
    int update(EduStudent student);
    int deleteById(Long id);
}
