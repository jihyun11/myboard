package com.jihyun.myboard.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageController {

    public String uploadDir = "/Users/mozzi/Desktop/선생님PR용/src/main/resources/static/img/";

    @GetMapping("/image/{imageName}")
    @ResponseBody
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
}
