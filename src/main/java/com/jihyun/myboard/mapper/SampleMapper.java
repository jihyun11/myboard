package com.jihyun.myboard.mapper;

import com.jihyun.myboard.entity.Sample;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleMapper {
    public String selectName();

    public Sample selectPersnal();

    public void insertPersnal(String name, String phone, String email, String address);

}
