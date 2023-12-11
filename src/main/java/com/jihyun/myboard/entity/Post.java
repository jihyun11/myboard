package com.jihyun.myboard.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;

@Getter
@Setter
public class Post {

    private String id; // 식별용 아이디

    private String content; // 내용

    private String writer; // 작성자

}
