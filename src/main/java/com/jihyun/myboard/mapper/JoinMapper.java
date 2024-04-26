package com.jihyun.myboard.mapper;
import com.jihyun.myboard.dto.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinMapper {
    public void insertJoin(String username, String password);

    public int checkUsernameExist(String username);

    public LoginDTO selectLogin(String username, String password);
}
