package com.demo.hibernate.utils;

import java.util.HashMap;
import java.util.Map;

public class ResUtils {

    public static Map<String, Object> success(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1000);
        map.put("msg", msg);
        return map;
    }

    public static Map<String, Object> success() {
        return success("成功");
    }

    public static Map<String, Object> success(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1000);
        map.put("msg", "成功");
        map.put("data", data);
        return map;
    }

}
