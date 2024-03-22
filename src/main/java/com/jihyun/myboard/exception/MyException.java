package com.jihyun.myboard.exception;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MyException extends RuntimeException {
    public MyException(String text, UnsupportedEncodingException e) {
        super(text, e);
    }

    public MyException(String text, IOException e) {
        super(text, e);
    }
}
