package com.cdsxt.action;

import com.cdsxt.po.Dept;
import com.cdsxt.po.Emp;
import com.cdsxt.service.DeptService;
import com.cdsxt.service.impl.DeptServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeptServlet extends HttpServlet {

    private DeptService deptService = new DeptServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mark = request.getParameter("mark");
        if ("queryAllDept".equals(mark)) {
            queryAllDept(request, response);
        } else if ("queryAllEmpByDeptno".equals(mark)) {
            queryAllEmpByDeptno(request, response);
        }
    }

    // 查询所有部门
    private void queryAllDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 查询所有部门, 并返回 List 集合
        List<Dept> depts = deptService.queryAllDept();
        request.setAttribute("depts", depts);
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }

    // 查询指定部门的所有员工
    private void queryAllEmpByDeptno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deptno = Integer.parseInt(request.getParameter("deptno"));

        List<Emp> emps = deptService.queryAllEmpByDeptno(deptno);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        response.getWriter().write(gson.toJson(emps));
    }
}
