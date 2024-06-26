package com.jihyun.myboard.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exercise {

    private String id;

    private String content;

    private String writer;

    private String filename;

    public Exercise(String id) {
        this.id = id;
    }
}
