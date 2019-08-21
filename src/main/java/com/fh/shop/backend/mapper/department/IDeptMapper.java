package com.fh.shop.backend.mapper.department;

import com.fh.shop.backend.po.department.Department;

import java.util.List;

public interface IDeptMapper {
    List<Department> findDeptList();

    void addDeptInfo(Department dept);

    void delDeptInfo(List<Integer> idsList);

    void updateDeptInfo(Department dept);
}
