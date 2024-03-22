package com.jihyun.myboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }


    @PostMapping("/login")
    public String login(HttpSession session,
                        @RequestParam String username) {
        session.setAttribute("username", username);
        return "redirect:/greeting";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        return "login";
    }
}
