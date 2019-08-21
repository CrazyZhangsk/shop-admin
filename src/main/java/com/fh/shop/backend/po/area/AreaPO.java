package com.fh.shop.backend.po.area;

import java.io.Serializable;

public class AreaPO implements Serializable {

    private static final long serialVersionUID = -3988085971028476886L;

    private Integer id;

    private String areaName;

    private Integer pId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}
