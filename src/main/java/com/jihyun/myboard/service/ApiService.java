package com.jihyun.myboard.service;

//import org.h2.util.json.JSONObject;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@Service
public class ApiService {

    private static final String CLIENT_ID = "9qxRfJyQ8EuloaUtdXur"; // 클라이언트 아이디
    private static final String CLIENT_SECRET = "XlgDyxer6x"; // 클라이언트 시크릿
    private static final String API_URL = "https://openapi.naver.com/v1/papago/n2mt";

    String a;

    public String translate(String text, String srcLang, String targetLang) {
        try {
            a = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("인코딩 실패", e);
        }

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", CLIENT_ID);
        requestHeaders.put("X-Naver-Client-Secret", CLIENT_SECRET);

        String responseBody = post(API_URL, requestHeaders, a, srcLang, targetLang);
        System.out.println("responseBody: " + responseBody);

        // JSON 객체로 파싱
        JSONObject jsonObj = new JSONObject(responseBody);
        // 'translatedText' 값을 추출
        String translatedText = jsonObj.getJSONObject("message").getJSONObject("result").getString("translatedText");

        System.out.println(translatedText);
        return translatedText;


    }

    public static String post(String apiUrl, Map<String, String> requestHeaders, String text, String srcLang, String targetLang) {
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source="+srcLang+"&target="+targetLang+"&text=" + text; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
        try {
            con.setRequestMethod("POST");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
