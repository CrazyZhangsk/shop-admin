package com.fh.shop.backend.controller.area;

import com.fh.shop.backend.biz.area.IAreaService;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.area.AreaPO;
import com.fh.shop.backend.response.AreaResponse;
import com.mysql.fabric.xmlrpc.base.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/area")
public class AreaController {

    @Resource(name="areaService")
    private IAreaService areaService;

    @RequestMapping("cityList")
    @ResponseBody
    public AreaResponse findCity(Integer pId){
        AreaResponse areaResponse =  areaService.findCity(pId);
       return areaResponse;
    }

    //地区查询
    @RequestMapping("findAreaList")
    @ResponseBody
    public ServerResponse findAreaList(){
        List<AreaPO> areaList = areaService.findAreaList();
        return ServerResponse.success(areaList);
    }

    @RequestMapping("delAreaInfo")
    @ResponseBody
    public ServerResponse delAreaInfo(@RequestParam("idsList[]") List<Integer> idsList){
        areaService.delAreaInfo(idsList);
        return ServerResponse.success();
    }

    @RequestMapping("updateAreaInfo")
    @ResponseBody
    public ServerResponse updateAreaInfo(AreaPO areaPO) {
        areaService.updateAreaInfo(areaPO);
        return ServerResponse.success();
    }

    @RequestMapping("addAreaInfo")
    @ResponseBody
    public ServerResponse addAreaInfo(AreaPO areaPO){
        areaService.addAreaInfo(areaPO);
        return ServerResponse.success(areaPO);
    }

    @RequestMapping("toAreaList")
    public String toAreaList() {
        return "area/areaList";
    }


}
