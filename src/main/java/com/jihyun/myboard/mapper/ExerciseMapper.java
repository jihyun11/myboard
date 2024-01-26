package com.jihyun.myboard.mapper;

import com.jihyun.myboard.entity.Exercise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExerciseMapper {
    public void insertEx(String content, String writer);

    public List<Exercise> selectEx();

    public void deleteEx(String id, String content, String writer);

    public Exercise exerciseSelectDetail(String idValue);

    public void exerciseUpdateDetail(String id, String content, String writer);
}
