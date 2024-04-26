package com.jihyun.myboard.controller;

import com.jihyun.myboard.service.JoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class JoinController {

    private final JoinService joinService;

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping("/join")
    public String join() {
        return "/join";
    }

    @PostMapping("/join")
    public String createMember(@RequestParam("username") String username,
                               @RequestParam("password") String password) {
        joinService.insertJoin(username, password);

        log.info(username, password);
        return "redirect:/login";
    }




}
