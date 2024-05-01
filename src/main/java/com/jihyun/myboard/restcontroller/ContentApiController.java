package com.jihyun.myboard.restcontroller;

import com.jihyun.myboard.dto.ContentDTO;
import com.jihyun.myboard.exception.MyException;
import com.jihyun.myboard.service.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ContentApiController {
    private final ContentService contentService;

    @Autowired
    public ContentApiController(ContentService contentService) {
        this.contentService = contentService;
    }

//    @RequestBody 방식으로 보내기
    @PostMapping("/api/content")
    public ContentDTO getContentDTO(@RequestBody ContentDTO contentDTO) {
        if (!contentDTO.getRole().equals("admin")) {
            throw new MyException("admin이 아닙니다");
        }
        log.info(contentDTO.getContent(), contentDTO.getAuthor());
        contentService.insertContent(contentDTO.getContent(), contentDTO.getAuthor());

        return contentDTO;
    }
}
