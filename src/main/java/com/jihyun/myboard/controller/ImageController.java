package com.jihyun.myboard.controller;

import com.jihyun.myboard.entity.Exercise;
import com.jihyun.myboard.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ImageController {

    public String uploadDir = "/Users/mozzi/Desktop/선생님PR용/src/main/resources/static/img/";
    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/image/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws MalformedURLException {
        // 이미지 파일 경로 설정
        Path imagePath = Paths.get(uploadDir).resolve(imageName);
        Resource resource = new UrlResource(imagePath.toUri());

        // 이미지 파일이 존재하는지 확인
        if (resource.exists() && resource.isReadable()) {
            // 이미지 파일을 읽어서 응답으로 전달
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // 이미지 파일의 MIME 타입 설정
                    .body(resource);
        } else {
            // 이미지 파일이 없을 경우 404 에러 응답
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/text")
    public List<Exercise> restTest(){
        Exercise exercise1 = new Exercise("1");
        Exercise exercise2 = new Exercise("2");
        Exercise exercise3 = new Exercise("3");

        List<Exercise> list = new ArrayList<>();
        list.add(exercise1);
        list.add(exercise2);
        list.add(exercise3);

        return list;
    }

//    @PostMapping("/api/exercise")
//    public ResponseEntity<String> insertExercise(@RequestBody Exercise exercise) {
//        String content = exercise.getContent();
//        String writer = exercise.getWriter();
//
//        if (content == null || writer == null) {
//            return new ResponseEntity<>("내용이 비었음.", HttpStatus.BAD_REQUEST);
//        }
//
//        exerciseService.insertEx(content, writer, null);
//
//        // 여기서 등록한 내용을 JSON으로 반환하거나 다른 작업을 수행할 수 있음
//        String responseBody = "등록된 내용: " + content + ", " + writer;
//        return new ResponseEntity<>(responseBody, HttpStatus.OK);
//    }
}
