package com.cdsxt.po;

import java.util.HashSet;
import java.util.Set;

public class Dept {

    private Integer deptno;
    private String dname;
    private String loc;

    // 保存员工的集合
    private Set<Emp> emps = new HashSet<>();

    public Set<Emp> getEmps() {
        return emps;
    }

    public void setEmps(Set<Emp> emps) {
        this.emps = emps;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
