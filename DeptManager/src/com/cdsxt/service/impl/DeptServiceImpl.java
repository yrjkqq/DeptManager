package com.cdsxt.service.impl;

import com.cdsxt.dao.DeptDao;
import com.cdsxt.dao.impl.DeptDaoImpl;
import com.cdsxt.po.Dept;
import com.cdsxt.po.Emp;
import com.cdsxt.service.DeptService;

import java.util.List;

public class DeptServiceImpl implements DeptService {

    private DeptDao deptDao = new DeptDaoImpl();

    @Override
    public List<Dept> queryAllDept() {
        return deptDao.queryAllDept();
    }

    @Override
    public List<Emp> queryAllEmpByDeptno(int deptno) {
        return deptDao.queryAllEmpByDeptno(deptno);
    }
}
