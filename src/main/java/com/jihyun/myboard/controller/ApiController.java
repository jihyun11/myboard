package com.jihyun.myboard.controller;

import com.jihyun.myboard.service.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/word")
    public String getTranslate() {
        return "word";
    }

    @PostMapping("/word")
    public String translate(@RequestParam("src_lang") String srcLang,
                            @RequestParam("target_lang") String targetLang,
                            @RequestParam("text") String text,
                            Model model) {
        String result = apiService.translate(text, srcLang, targetLang);

        model.addAttribute("originalText", text);
        model.addAttribute("result", result);

        System.out.println("타임리프 result: " + result);
        System.out.println("post wordtran 실행");

       return "word";
    }
}
