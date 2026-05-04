package com.homefun.common.utils;

import java.util.UUID;

public class UUIDUtils {

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String simpleUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String fastUUID() {
        return UUID.randomUUID().toString();
    }
}
