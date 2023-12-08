package com.jihyun.myboard.controller;

import com.jihyun.myboard.entity.Post;
import com.jihyun.myboard.service.PostService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 조회
    @GetMapping("/guest")
    public String guest(Model model) {
        List<Post> post = postService.selectPost();
        model.addAttribute("post", post);
        return "guest";
    }

    // 게시글 등록
    @PostMapping("/guest")
    public String insertPost(@RequestParam String content,
                             @RequestParam String writer) {
        postService.insertPost(content, writer);
        return "redirect:/guest";
    }

    // 게시글 삭제
    @PostMapping("/delete")
    public String deletePost(@RequestParam String content,
                             @RequestParam String writer) {
        postService.deletePost(content, writer);
        return "redirect:/guest";
    }

    // 이곳에 게시글 수정(Post) 컨트롤러를 작성합니다.

}
