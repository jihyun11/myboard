package com.jihyun.myboard.mapper;

import com.jihyun.myboard.entity.Exercise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExerciseMapper {

    List<Exercise> contentListView(int offset);
    int getContentCount(String keyword);

    public void insertEx(String content, String writer);

    public void deleteEx(String id, String content, String writer);

    public Exercise exerciseSelectDetail(String idValue);

    public void exerciseUpdateDetail(String id, String content, String writer);

    List<Exercise> kewordSelect(int offset, String keyword);
}
