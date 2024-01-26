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

    public void insertEx(String content, String writer) {
        exerciseMapper.insertEx(content, writer);
    }

    public List<Exercise> selectEx() {
        List<Exercise> selectExResult = exerciseMapper.selectEx();
        return selectExResult;
    }

    public void deleteEx(String id, String content, String writer) {
        exerciseMapper.deleteEx(id, content, writer);
    }

    public Exercise exerciseSelectDetail(String idValue) {
        Exercise exerciseSelectDetail = exerciseMapper.exerciseSelectDetail(idValue);
        return exerciseSelectDetail;
    }

    public void exerciseUpdateDetail(String id, String content, String writer) {
        exerciseMapper.exerciseUpdateDetail(id, content, writer);

    }
}
