package com.jihyun.myboard.service;

import com.jihyun.myboard.dto.ContentDTO;
import com.jihyun.myboard.mapper.ContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContentService {

    private final ContentMapper contentMapper;

    @Autowired
    public ContentService(ContentMapper contentMapper) {
        this.contentMapper = contentMapper;
    }

    public void insertContent(String content, String author) {
        contentMapper.insertContent(content, author);
    }

}
