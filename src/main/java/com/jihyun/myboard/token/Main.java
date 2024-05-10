package com.jihyun.myboard.token;

import com.jihyun.myboard.dto.ContentDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

public class Main {
    private static String SECRET_KEY = "AFHSUSFIHQASFEUOFHQFUOIQHFUIQWHFIOQHFIUAF";

    public static void main(String[] args) {
        String token = makeJwtToken();
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJteXRlYW0iLCJpYXQiOjE3MTQyNzAxNDUsImV4cCI6MTcxNDI3MTk0NSwiaWQiOiJteUlkIiwiZW1haWwiOiJteUlkQGdtYWlsLmNvbSIsInJvbGUiOiJhZG1pbiJ9.oeusGPuP7HqGWZ1n9Y38WqRz7sbAfwXCpNQjHfzbEtE";
        System.out.println(token);
        parseJwtToken(token);
    }

//    토큰 만들기
    public static String makeJwtToken() {
        ContentDTO contentDTO = new ContentDTO();
        Date now = new Date();
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("contentUser")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
                .claim("username", contentDTO.getAuthor())
                .claim("role", "user")
                .signWith(key)
                .compact();
    }

//    토큰 복호화 하여 본문(Payload) 가져오기
    public static Claims parseJwtToken(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        System.out.println("claims = " + claims.toString());

        return claims;
    }
}