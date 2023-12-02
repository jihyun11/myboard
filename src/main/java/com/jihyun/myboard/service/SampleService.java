package com.jihyun.myboard.service;

import com.jihyun.myboard.mapper.SampleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    private final SampleMapper sampleMapper;

    @Autowired
    public SampleService(SampleMapper sampleMapper) {
        this.sampleMapper = sampleMapper;
    }

    public void selectSample() {
        sampleMapper.selectSample();
    }
}
