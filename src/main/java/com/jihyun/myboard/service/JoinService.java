package com.jihyun.myboard.service;

import com.jihyun.myboard.dto.LoginDTO;
import com.jihyun.myboard.mapper.JoinMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JoinService {

    private final JoinMapper joinMapper;

    @Autowired
    public JoinService(JoinMapper joinMapper) {
        this.joinMapper = joinMapper;
    }

    public void insertJoin(String username, String password) {
        joinMapper.insertJoin(username, password);
    }

    public int checkUsernameExist(String username) {
        return joinMapper.checkUsernameExist(username);
    }

    public LoginDTO selectLogin(String username, String password) {
        return joinMapper.selectLogin(username, password);
    }
}
