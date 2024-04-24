package com.jihyun.myboard.service;

import com.jihyun.myboard.entity.Exercise;
import com.jihyun.myboard.mapper.ExerciseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseMapper exerciseMapper;

    @Autowired
    public ExerciseService(ExerciseMapper exerciseMapper) {
        this.exerciseMapper = exerciseMapper;
    }

    //페이지네이션 포함
    public List<Exercise> getContentListView(int offset) {
        return exerciseMapper.contentListView(offset);
    }

    //페이지네이션
    public int getContentCount(String keyword) {
        return exerciseMapper.getContentCount(keyword);
    }

    public void insertEx(String content, String writer, String filename) {
        exerciseMapper.insertEx(content, writer, filename);
    }

    public void deleteEx(String id, String content, String writer) {
        exerciseMapper.deleteEx(id, content, writer);
    }

    public Exercise exerciseSelectDetail(String idValue) {
        Exercise exerciseSelectDetail = exerciseMapper.exerciseSelectDetail(idValue);
        return exerciseSelectDetail;
    }

    public void exerciseUpdateDetail(String id, String content, String writer, String filename) {
        exerciseMapper.exerciseUpdateDetail(id, content, writer, filename);

    }

    // 검색어
    public List<Exercise> selectKeyword(int offset, String keyword) {
        return exerciseMapper.selectKeyword(offset, keyword);
    }
}
