package com.fh.shop.backend.common;

import java.util.HashMap;
import java.util.Map;

public final class SystemConstants {
    public static  final String PRODUCT_IMAGE_PATH = "sysimages/product/";

    public static  final String PRODUCT_IMAGE_PATH_SOS = "https://zsk-1258853164.cos.ap-beijing.myqcloud.com/";

    public  static final String IMAGECODE = "imageCode";

    public  static final String ORDER_DIR = "order[0][dir]";

    public  static final String ORDER_COLUMN = "order[0][column]";

    public static final Map FIELD_MAPPING_STATIC = new HashMap();

    public static final String FIELD_MAPPING_STRING = "{entryTimeStr:'entryTime',updateTimeStr:'updateTime',productPrice:'price'}";

    public static final String JSESSIONID = "fh_id";

    public static final String SESSION_CODE="code:";

    public static final String SESSION_USER="user:";
    public static final String SESSION_DOMAIN="10.10.10.13";
    static {
        FIELD_MAPPING_STATIC.put("entryTimeStr","entryTime");
        FIELD_MAPPING_STATIC.put("updateTimeStr","updateTime");
        FIELD_MAPPING_STATIC.put("productPrice","price");
        FIELD_MAPPING_STATIC.put("birthdayStr","birthday");
        FIELD_MAPPING_STATIC.put("salary","salary");
    }
}
