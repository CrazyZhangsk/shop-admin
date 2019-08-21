package com.fh.shop.backend.biz.area;

import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.area.AreaPO;
import com.fh.shop.backend.response.AreaResponse;

import java.util.List;

public interface IAreaService {

    List<AreaPO> findAreaList();

    void delAreaInfo(List<Integer> idsList);

    void updateAreaInfo(AreaPO areaPO);

    void addAreaInfo(AreaPO areaPO);

    AreaResponse findCity(Integer pId);
}
