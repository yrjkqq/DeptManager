<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Hello World!</title>
    <script src="js/jquery-1.12.4.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>
<div class="container-fluid">
    <%--导航栏--%>
    <div class="row">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">员工管理系统</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">首页 <span class="sr-only">(current)</span></a></li>
                        <li><a href="#">关于我们</a></li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">登陆</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false">个人中心 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </div>
    <%--表格区域--%>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">部门列表</div>
                <!-- Table -->
                <table class="table table-bordered">
                    <tr>
                        <th>部门编号</th>
                        <th>部门名称</th>
                        <th>部门地址</th>
                        <th>所有员工</th>
                    </tr>
                    <%-- 查询出来的内容置于此处--%>
                    <c:forEach items="${depts}" var="dept">
                        <tr>
                            <td>${dept.deptno}</td>
                                <%-- 部门名称注册点击事件, 点击后展开员工表--%>
                            <td>
                                <a dhref="#" onclick="showAllEmp(${dept.deptno}, event)">${dept.dname}</a>
                            </td>
                            <td>${dept.loc}</td>
                            <td>
                                <c:forEach items="${dept.emps}" var="emp">
                                    ${emp.ename},
                                </c:forEach>
                            </td>
                        </tr>
                        <tr class="hide success" id="${dept.deptno}row">
                            <td colspan="3">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>姓名</th>
                                        <th>工作</th>
                                        <th>上级</th>
                                        <th>雇佣日期</th>
                                        <th>薪水</th>
                                        <th>补助</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td colspan="7" class="text-center">暂无数据</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </div>
    </div>
</div>

</body>
</html>
<script>
    function showAllEmp(deptno, event) {
        event.preventDefault();

        // 要隐藏和显示的行
        var $hideRow = $("#" + deptno + "row");
        // 切换 css 样式, 隐藏或显示
        $hideRow.toggleClass("hide");

        //  发出 ajax 请求获取所有员工
        $.ajax({
            url: "deptServlet.do",
            data: {
                "deptno": deptno,
                "mark": "queryAllEmpByDeptno"
            },
            type: "GET",
            dataType: "json"
        }).done(function (result) {

            // 插入数据的位置
            var $target = $hideRow.find("tbody");
            if (result && result.length) {
                // 先清空
                $hideRow.find("tbody").html("");

                $.each(result, function () {
                    var $tr = $("<tr>").appendTo($target); // 在 tbody 中追加新行
                    $("<td>" + this.empno + "</td>").appendTo($tr);
                    $("<td>" + this.ename + "</td>").appendTo($tr);
                    $("<td>" + this.job + "</td>").appendTo($tr);
                    $("<td>" + (this.mgr || '') + "</td>").appendTo($tr);
                    $("<td>" + this.hiredate + "</td>").appendTo($tr);
                    $("<td>" + this.sal + "</td>").appendTo($tr);
                    $("<td>" + (this.comm || '0') + "</td>").appendTo($tr);
                });
            }


        });
    }
</script>
