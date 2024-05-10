package com.jihyun.myboard.service;

import com.jihyun.myboard.dto.ContentListDTO;
import com.jihyun.myboard.mapper.ContentListMapper;
import com.jihyun.myboard.mapper.ContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContentListService {

    private final ContentListMapper contentListMapper;

    @Autowired
    public ContentListService(ContentListMapper contentListMapper) {
        this.contentListMapper = contentListMapper;
    }

    public List<ContentListDTO> contentBookListView() {
        List<ContentListDTO> contentListResult = contentListMapper.contentBookListView();
        return contentListResult;
    }

}
