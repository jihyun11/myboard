package com.jihyun.myboard.controller;

import com.jihyun.myboard.entity.Sample;
import com.jihyun.myboard.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SampleController {

    private final SampleService sampleService;

    @Autowired
    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/sample")
    public String sample(Model model) {
        Sample persnal = sampleService.selectPersnal();
        model.addAttribute("persnal", persnal);
        System.out.println("select문 실행");
        return "sample";
    }

    @PostMapping("/sample")
    public String insertPersnal(@RequestParam("name") String name,
                              @RequestParam("phone") String phone,
                              @RequestParam("email") String email,
                              @RequestParam("address") String address) {

        sampleService.insertPersnal(name, phone, email, address);
        System.out.println("insert문 실행");

        return "redirect:/sample";

    }

}
