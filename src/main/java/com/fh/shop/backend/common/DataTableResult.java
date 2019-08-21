package com.fh.shop.backend.common;

import java.io.Serializable;

public class DataTableResult implements Serializable {
    private static final long serialVersionUID = -6296354744750178562L;

    private Object data;

    //表示请求次数
    private Integer draw;

    //过滤后的总记录数
    private Long recordsFiltered;

    //recordsTotal
    private Long recordsTotal;

    public static DataTableResult buildDataTableResult(Object data, Integer draw, Long recordsFiltered, Long recordsTotal){
        return new DataTableResult(data, draw, recordsFiltered, recordsTotal);
    }

    private DataTableResult(Object data, Integer draw, Long recordsFiltered, Long recordsTotal) {
        this.data = data;
        this.draw = draw;
        this.recordsFiltered = recordsFiltered;
        this.recordsTotal = recordsTotal;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
}
