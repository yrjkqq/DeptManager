package com.cdsxt.dao;

import com.cdsxt.po.Dept;
import com.cdsxt.po.Emp;

import java.util.List;

public interface DeptDao {

    List<Dept> queryAllDept();

    List<Emp> queryAllEmpByDeptno(int deptno);

}
