package com.fh.shop.backend.po;

import java.io.Serializable;

public class DataTablePage implements Serializable {
    private static final long serialVersionUID = -4044276308437661410L;

    private Integer start;
    private Integer length;
    private Integer draw;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }
}
