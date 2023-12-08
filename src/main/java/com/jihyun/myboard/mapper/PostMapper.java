package com.jihyun.myboard.mapper;

import com.jihyun.myboard.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    public void insertPost(String content, String writer); // 게시글 등록

    public List<Post> selectPost(); // 게시글 조회

    public void deletePost(String content, String writer); // 게시글 삭제

    // 이곳에 게시글 수정 메소드를 작성합니다.
}
