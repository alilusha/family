package com.homefun.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

public class JsonUtils {

    public static String toJsonString(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object);
    }

    public static String toJsonStringPretty(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object, new com.alibaba.fastjson2.writer.ObjectWriterProvider.Options(true, true));
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        return JSON.parseObject(text, clazz);
    }

    public static JSONObject parseObject(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        return JSON.parseObject(text);
    }
}
