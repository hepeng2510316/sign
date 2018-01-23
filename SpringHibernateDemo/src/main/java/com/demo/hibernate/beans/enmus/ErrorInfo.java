package com.demo.hibernate.beans.enmus;

public enum ErrorInfo {

    PARAMS_ERROR(1001, "参数错误！");


    private int value;

    private String name;

    ErrorInfo(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}