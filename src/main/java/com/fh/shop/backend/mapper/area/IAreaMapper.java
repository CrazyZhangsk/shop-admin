package com.fh.shop.backend.mapper.area;

import com.fh.shop.backend.po.area.AreaPO;

import java.util.List;

public interface IAreaMapper {
    List<AreaPO> findAreaList();

    void delAreaInfo(List<Integer> idsList);

    void updateAreaInfo(AreaPO areaPO);

    void addAreaInfo(AreaPO areaPO);
}
