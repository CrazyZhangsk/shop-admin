package com.fh.shop.backend.biz;

public class BaseBiz {
    protected String getOrderColumnsData(String column) {
        return "columns[" + column + "][data]";
    }
}
