package com.cdsxt.dao.impl;

import com.cdsxt.dao.DeptDao;
import com.cdsxt.po.Dept;
import com.cdsxt.po.Emp;
import com.cdsxt.util.HibUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class DeptDaoImpl implements DeptDao {

    // 查询所有部门
    @Override
    public List<Dept> queryAllDept() {
        Session session = HibUtils.getSession();
        Query query = session.createQuery("from Dept d");
        List<Dept> depts = query.list();
        System.out.println("dao: " + HibUtils.getSession().hashCode());
        // session.close();
        return depts;
    }

    @Override
    public List<Emp> queryAllEmpByDeptno(int deptno) {
        Session session = HibUtils.getSession();
        Query query = session.createQuery("from Emp e where e.dept.deptno = ?");
        query.setInteger(0, deptno);
        List<Emp> emps = query.list();
        // session.close();
        return emps;
    }
}
