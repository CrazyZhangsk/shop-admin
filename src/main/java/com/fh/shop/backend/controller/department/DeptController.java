package com.fh.shop.backend.controller.department;

import com.fh.shop.backend.biz.department.IDeptService;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.department.Department;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Resource(name="deptService")
    private IDeptService deptService;

    @RequestMapping("findDeptList")
    @ResponseBody public ServerResponse findDeptList(){
        List<Map> retMap = deptService.findDeptList();
        return ServerResponse.success(retMap);
    }

    @RequestMapping("updateDeptInfo")
    @ResponseBody
    public ServerResponse updateDeptInfo(Department dept) {
        deptService.updateDeptInfo(dept);
        return ServerResponse.success();
    }

    @RequestMapping("addDeptInfo")
    @ResponseBody
    public ServerResponse addDeptInfo(Department dept){
        deptService.addDeptInfo(dept);
        return ServerResponse.success(dept);
    }

    @RequestMapping("delDeptInfo")
    @ResponseBody
    public ServerResponse delDeptInfo(@RequestParam("idsStr[]") List<Integer> ids){
        deptService.delDeptInfo(ids);
        return ServerResponse.success();
    }

}
