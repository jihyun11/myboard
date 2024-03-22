package com.jihyun.myboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(HttpSession session,
                           Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "greeting";
    }
}
