package com.jihyun.myboard.aop;

import com.jihyun.myboard.dto.ContentDTO;
import com.jihyun.myboard.exception.MyException;
import com.jihyun.myboard.token.TokenUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@Aspect
public class RestAOP {
    @Before("execution(* com.jihyun.myboard.restcontroller.ContentApiController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before calling method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.jihyun.myboard.restcontroller.ContentApiController.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After calling method: " + joinPoint.getSignature().getName());
    }

    @Before("execution(* com.jihyun.myboard.restcontroller.ContentApiController.getContentDTO(..)) && args(contentDTO, jwt)")
    public void checkAuthorization(@RequestBody ContentDTO contentDTO,
                                   @RequestHeader("Authorization") String jwt) {
        Claims claims = TokenUtil.parseJwtToken(jwt);
        String tokenRole = (String) claims.get("role");
        System.out.println("유저의 권한: " + tokenRole + " AOP 클래스에서 실행");

        if (!tokenRole.equals("admin")) {
            throw new MyException("admin이 아닙니다");
        }
    }
}
