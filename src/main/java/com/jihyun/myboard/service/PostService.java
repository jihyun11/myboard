package com.jihyun.myboard.service;

import com.jihyun.myboard.entity.Post;
import com.jihyun.myboard.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostMapper postMapper;

    @Autowired
    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public void insertPost(String content, String writer) {
        postMapper.insertPost(content, writer);
    } // 게시글 등록

    public List<Post> selectPost() { // 게시글 조회
        List<Post> post = postMapper.selectPost();
        return post;
    }

    public void deletePost(String content, String writer) {
        postMapper.deletePost(content, writer);
    } // 게시글 삭제

    public void updatePost(String content, String writer, String id) {
        postMapper.updatePost(content, writer, id);
        System.out.println("업데이트 서비스");
    } // 게시글 수정


}
