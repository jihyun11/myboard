package com.jihyun.myboard.controller;

import com.jihyun.myboard.entity.Exercise;
import com.jihyun.myboard.service.ExerciseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class ExerciseController {

    private final ExerciseService exerciseService;
    public String uploadDir = "/Users/mozzi/Desktop/선생님PR용/src/main/resources/static/img/";

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercise")
    public String exersice(Model model,
                           @RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "keyword",  required = false, defaultValue = "") String keyword) {
        int pageSize = 5; // 페이지당 게시글 수
        int totalCount = exerciseService.getContentCount(keyword);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        int offset = (page - 1) * pageSize;
        List<Exercise> contentList = exerciseService.selectKeyword(offset, keyword);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("contentList", contentList);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        // Cmd + Shift + R 정해진 문자열 바꾸기
        // Cmd + Shift + F 전체 찾기

        log.info(keyword);

        return "/exercise";
    }

    @PostMapping(value = "/exercise", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String insertEx(@RequestParam String content,
                           @RequestParam String writer,
                           @RequestPart MultipartFile document) {
        if (document.isEmpty()) {
            exerciseService.insertEx(content, writer, null);
            return "redirect:/exercise";
        }

        try {
            String filename = fileUpload(document);
            exerciseService.insertEx(content, writer, filename);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        log.info("등록한 내용: {}, {}", content, writer);
        return "redirect:/exercise";
    }

    // 파일 업로드
    private String fileUpload(MultipartFile document) throws IOException {
        // 파일 저장 경로에 업로드된 파일 저장
        String filename = document.getOriginalFilename();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentTime = dateFormat.format(new Date());
        String fileFullName =  currentTime + filename;
        document.transferTo(new File(uploadDir + fileFullName));
        return fileFullName;
    }

    @PostMapping( "/deleteEx")
    public String deleteEx(@RequestParam String id,
                           @RequestParam String content,
                           @RequestParam String writer,
                           @RequestParam String filename) {

        if (filename.equals(null)) {
            exerciseService.deleteEx(id, content, writer);
            return "redirect:/exercise";
        }

        // 파일 저장 경로에 업로드된 파일 삭제
        String fileroute = uploadDir + filename;
        File fileToDelete = new File(fileroute);
        fileToDelete.delete();
        exerciseService.deleteEx(id, content, writer);

        log.info("삭제된 내용: {}, {}, {}", id, content, writer);

        return "redirect:/exercise";
    }

    @GetMapping("/exercise/detail/{idValue}")
    public String exerciseSelectDetail(@PathVariable("idValue") String idValue, Model model,
                                       @RequestParam(value = "page", defaultValue = "1") int page) {
        Exercise exercise = (exerciseService.exerciseSelectDetail(idValue));
        model.addAttribute("exercise", exercise);
        return "/exerciseDetail";
    }

    @PostMapping(value = "/exercise/detail",  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String exerciseUpdateDetail(@RequestParam String id,
                                       @RequestParam String content,
                                       @RequestParam String writer,
                                       @RequestPart MultipartFile document) {

        if (document.isEmpty()) {
            exerciseService.exerciseUpdateDetail(id, content, writer, null);
            return "redirect:/exercise";
        }

        try {
            // 파일 저장 경로에 업로드된 파일 저장
            String filename = fileUpload(document);
            exerciseService.exerciseUpdateDetail(id, content, writer, filename);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        log.info("등록한 내용: {}, {}", content, writer);
        return "redirect:/exercise";
    }
}
