package com.jihyun.myboard.mapper;
import com.jihyun.myboard.dto.ContentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentMapper {
    public void insertContent(String content, String author);
}
