package com.jihyun.myboard.controller;

import com.jihyun.myboard.entity.Exercise;
import com.jihyun.myboard.service.ExerciseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercise")
    public String exersice(Model model) {
        List<Exercise> selectExResult = exerciseService.selectEx();
        model.addAttribute("selectExResult", selectExResult);
        return "/exercise";
    }

    @PostMapping("/exercise")
    public String insertEx(@RequestParam String content,
                           @RequestParam String writer) {
        exerciseService.insertEx(content, writer);
        log.info("등록한 내용: {}, {}", content, writer);
        return "redirect:/exercise";
    }

    @PostMapping("/deleteEx")
    public String deleteEx(@RequestParam String id,
                           @RequestParam String content,
                           @RequestParam String writer) {
        exerciseService.deleteEx(id, content, writer);
        log.info("삭제된 내용: {}, {}, {}", id, content, writer);
        return "redirect:/exercise";
    }

    @GetMapping("/exercise/{idValue}")
    public String exerciseSelectDetail(@PathVariable("idValue") String idValue, Model model) {
        Exercise exercise = (exerciseService.exerciseSelectDetail(idValue));
        model.addAttribute("exercise", exercise);
        return "/exerciseDetail";
    }

    @PostMapping("/exercise/{idValue}")
    public String exerciseUpdateDetail(@PathVariable("idValue") String idValue, //바로 링크에 있는 idValue 값을 넣으면 안되는 이유
                                       @RequestParam String id,
                                       @RequestParam String content,
                                       @RequestParam String writer) {
//        exerciseService.exerciseUpdateDetail(idValue, content, writer); 이건 안되는 이유?
        exerciseService.exerciseUpdateDetail(id, content, writer);
        return "redirect:/exercise";
    }
}
