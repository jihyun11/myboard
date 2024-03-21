package com.jihyun.myboard.controller;

import com.jihyun.myboard.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/word")
    public String getTranslate(HttpSession session) {
        String username = (String) session.getAttribute("username");

        if(username ==  null) {
            return "login";
        } else return "word";
    }

    @PostMapping("/word")
    public String translate(@RequestParam("src_lang") String srcLang,
                            @RequestParam("target_lang") String targetLang,
                            @RequestParam("text") String text,
                            Model model) {
        String result = apiService.translate(text, srcLang, targetLang);

        model.addAttribute("originalText", text);
        model.addAttribute("result", result);

        log.info("타임리프 result: " + result);
        log.info("post wordtran 실행");

       return "word";
    }
}
