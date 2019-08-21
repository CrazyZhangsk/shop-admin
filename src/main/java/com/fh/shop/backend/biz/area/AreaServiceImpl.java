package com.fh.shop.backend.biz.area;

import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.mapper.area.IAreaMapper;
import com.fh.shop.backend.po.area.AreaPO;
import com.fh.shop.backend.response.AreaResponse;
import com.fh.shop.utils.CacheManager;
import com.fh.shop.utils.HttpClientUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value="areaService")
public class AreaServiceImpl implements IAreaService{

    @Autowired
    private IAreaMapper areaMapper;


    @Override
    public List<AreaPO> findAreaList() {
        CacheManager instance = CacheManager.getInstance();
        Object areaList = instance.getObj("areaList");
        if (areaList != null){
            return (List<AreaPO>) areaList;
        }
        List<AreaPO> areaListInfo = areaMapper.findAreaList();
        instance.putObj("areaList", areaListInfo);
        return areaListInfo;
    }

    @Override
    public void delAreaInfo(List<Integer> idsList) {
        areaMapper.delAreaInfo(idsList);
        CacheManager.getInstance().remove("areaList");
    }

    @Override
    public void updateAreaInfo(AreaPO areaPO) {
        areaMapper.updateAreaInfo(areaPO);
        CacheManager.getInstance().remove("areaList");
    }

    @Override
    public void addAreaInfo(AreaPO areaPO) {
        areaMapper.addAreaInfo(areaPO);
    }

    @Value("${city.url}")
    private String city_url;
    @Override
    public AreaResponse findCity(Integer pId) {
        Map<String,String> params = new HashMap<>();
        params.put("pId", String.valueOf(pId));
        String result = HttpClientUtils.sentPost(city_url, null, params);
        Gson gson = new Gson();
        AreaResponse areaResponse = gson.fromJson(result, AreaResponse.class);
        return areaResponse;
    }
}
