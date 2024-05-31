package com.jihyun.myboard.restcontroller;

import com.jihyun.myboard.dto.ContentDTO;
import com.jihyun.myboard.exception.MyException;
import com.jihyun.myboard.service.ContentService;
import com.jihyun.myboard.token.TokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    @PostMapping("/api/content")
    // 핵심 관점 - 게시글 저장하는 것
    // 부가적인 관점 - 저장할 때에 사용자의 권한을 확인하는 것
    public ContentDTO getContentDTO(@RequestBody ContentDTO contentDTO) {
        log.info(contentDTO.getContent(), contentDTO.getAuthor());
        contentService.insertContent(contentDTO.getContent(), contentDTO.getAuthor());
        return contentDTO;
    }

    @Transactional
    @PostMapping("/role/check")
    public String getRole(@RequestBody ContentDTO contentDTO) {
        log.info(contentDTO.getContent(), contentDTO.getAuthor());
        return "admin이 맞습니다";
    }
}
