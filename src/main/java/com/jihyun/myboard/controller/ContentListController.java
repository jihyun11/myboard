package com.jihyun.myboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentListController {

    @GetMapping("/contentlist")
    public String contentList() {
        return "/contentlist";
    }
}
