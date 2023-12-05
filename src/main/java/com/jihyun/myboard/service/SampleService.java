package com.jihyun.myboard.service;

import com.jihyun.myboard.entity.Sample;
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

//    public void selectSample() {
//        sampleMapper.selectSample();
//    }

    public String selectName() {
        String result = sampleMapper.selectName();
        return result;
    }

    public Sample selectPersnal() {
        Sample persnal = sampleMapper.selectPersnal();
        return persnal;
    }

    public void insertPersnal(String name, String phone, String email, String address) {
        sampleMapper.insertPersnal(name, phone, email, address);
    }
}
