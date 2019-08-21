package com.fh.shop.backend.biz.department;

import com.fh.shop.backend.mapper.department.IDeptMapper;
import com.fh.shop.backend.po.department.Department;
import com.fh.shop.utils.CacheManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "deptService")
public class DeptServiceImpl implements IDeptService{

    @Autowired
    private IDeptMapper deptMapper;
    @Override
    public List<Map> findDeptList() {
        CacheManager instance = CacheManager.getInstance();
        Object deptListInfo = instance.getObj("deptList");
        if (deptListInfo != null) {
            return (List<Map>) deptListInfo;
        }
        List<Map> retList = new ArrayList<>();
        List<Department> deptList = deptMapper.findDeptList();
        for (Department department : deptList) {
            Map retMap = new HashMap();
            retMap.put("name",department.getDeptName());
            retMap.put("pId",department.getpId());
            retMap.put("id",department.getId());
            retMap.put("remark",department.getRemark());
            retList.add(retMap);
        }
        instance.putObj("deptList",retList);
        return retList;
    }

    @Override
    public void addDeptInfo(Department dept) {
        deptMapper.addDeptInfo(dept);
        CacheManager.getInstance().remove("deptList");
    }

    @Override
    public void delDeptInfo(List<Integer> idsList) {
        deptMapper.delDeptInfo(idsList);
        CacheManager.getInstance().remove("deptList");
    }

    @Override
    public void updateDeptInfo(Department dept) {
        deptMapper.updateDeptInfo(dept);
        CacheManager.getInstance().remove("deptList");
    }
}
