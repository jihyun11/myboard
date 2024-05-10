package com.jihyun.myboard.restcontroller;

import com.jihyun.myboard.dto.ContentDTO;
import com.jihyun.myboard.dto.ContentListDTO;
import com.jihyun.myboard.exception.MyException;
import com.jihyun.myboard.service.ContentListService;
import com.jihyun.myboard.service.ContentService;
import com.jihyun.myboard.token.TokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ContentListApiController {

    private final ContentListService contentListService;

    @Autowired
    public ContentListApiController(ContentListService contentListService) {
        this.contentListService = contentListService;
    }


    @GetMapping("/content/book/list")
    public List<ContentListDTO> getContentBookList() {
        List<ContentListDTO> contentBookList = contentListService.contentBookListView();
        System.out.println(contentBookList);

        return contentBookList;
    }
}
