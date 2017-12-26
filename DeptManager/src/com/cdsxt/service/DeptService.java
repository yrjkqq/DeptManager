package com.cdsxt.service;

import com.cdsxt.po.Dept;
import com.cdsxt.po.Emp;

import java.util.List;

public interface DeptService {

    List<Dept> queryAllDept();

    List<Emp> queryAllEmpByDeptno(int deptno);
}
