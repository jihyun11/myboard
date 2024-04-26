package com.jihyun.myboard.restcontroller;

import com.jihyun.myboard.entity.Exercise;
import com.jihyun.myboard.service.ExerciseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Slf4j
@RestController
public class ExApiController {
    private final ExerciseService exerciseService;

    @Autowired
    public ExApiController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/jihyun")
    public Map<String, Object> rest() {
        Exercise exercise = new Exercise("1");
        Exercise exercise1 = new Exercise("2");
        Exercise exercise2 = new Exercise("3");

        List<Exercise> list = List.of(exercise, exercise1, exercise2);
        Map<String, Object> map = new HashMap<>();
        map.put("1", exercise);
        map.put("2", list);


        return map;
    }

    // exercise.html
    @CrossOrigin(origins = "*")
    @GetMapping("/api/ex")
    public Map<String, Object> exersice(
                           @RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "keyword",  required = false, defaultValue = "") String keyword) {
        int pageSize = 5; // 페이지당 게시글 수
        int totalCount = exerciseService.getContentCount(keyword);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        int offset = (page - 1) * pageSize;
        List<Exercise> contentList = exerciseService.selectKeyword(offset, keyword);

        Map<String, Object> map = new HashMap<>();
        map.put("totalPages", totalPages);
        map.put("contentList", contentList);
        map.put("page", page);

        return map;
    }

    // exerciseDetail.html
    @GetMapping("/api/ex/detail/{idValue}")
    public Exercise exerciseDetail(@PathVariable("idValue") String idValue) {
        Exercise detailNumber = (exerciseService.exerciseSelectDetail(idValue));

        return detailNumber;
    }

}
