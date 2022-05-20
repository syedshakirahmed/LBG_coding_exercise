package com.lloyds.util;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
    private static Gson gson = new Gson();
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T parseToObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
