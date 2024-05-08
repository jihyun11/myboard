package com.jihyun.myboard.restcontroller;

import com.jihyun.myboard.dto.LoginDTO;
import com.jihyun.myboard.entity.Exercise;
import com.jihyun.myboard.service.ExerciseService;
import com.jihyun.myboard.service.JoinService;
import com.jihyun.myboard.token.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class JoinApiController {
    private final JoinService joinService;

    @Autowired
    public JoinApiController(JoinService joinService) {
        this.joinService = joinService;
    }

    // join.html
    @GetMapping("/api/members/{username}")
    public int checkUsernameExist(@PathVariable("username") String username) {
        int usernameExist = joinService.checkUsernameExist(username);

        return usernameExist;
    }

    //@RequestBody 방식으로 보내기
//    @PostMapping("/join/login")
//    public String selectLogin(@RequestBody LoginDTO loginDTO) {
//        log.info(loginDTO.getUsernameIn(), loginDTO.getPasswordIn());
//
//        if(joinService.selectLogin(loginDTO.getUsernameIn(), loginDTO.getPasswordIn())) {
//            return "success";
//        } else return "fail";
//
//    }

    // @RequestParam 방식으로 보내기
    @PostMapping("/join/login")
    public LoginDTO selectLogin(@RequestParam("usernameIn") String username,
                              @RequestParam("passwordIn") String password) {
        log.info(username, password);
        LoginDTO loginDTO = joinService.selectLogin(username, password);

        TokenUtil tokenUtil = new TokenUtil();
        String jwt = tokenUtil.makeJwtToken(username, loginDTO.getRole());
        System.out.println(jwt);

        loginDTO.setJwt(jwt);

        return loginDTO;
    }
}
