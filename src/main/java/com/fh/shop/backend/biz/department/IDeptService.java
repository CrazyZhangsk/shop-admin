package com.fh.shop.backend.biz.department;

import com.fh.shop.backend.po.department.Department;

import java.util.List;
import java.util.Map;

public interface IDeptService {
    List<Map> findDeptList();

    void addDeptInfo(Department dept);

    void delDeptInfo(List<Integer> idsList);

    void updateDeptInfo(Department dept);
}
