package com.ifuture.demo.util;

import java.util.UUID;

public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
