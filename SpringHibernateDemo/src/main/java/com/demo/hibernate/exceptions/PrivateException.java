package com.demo.hibernate.exceptions;

import com.demo.hibernate.beans.enmus.ErrorInfo;
import lombok.ToString;

@ToString
public class PrivateException extends Exception {

    public int code;
    public String msg;

    public PrivateException(ErrorInfo errorInfo) {
        this.code = errorInfo.getValue();
        this.msg = errorInfo.getName();
    }

    public PrivateException(String msg) {
        this.code = 1001;
        this.msg = msg;
    }
}