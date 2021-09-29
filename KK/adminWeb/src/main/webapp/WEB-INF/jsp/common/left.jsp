<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="neu" uri="http://java.sun.com/jsp/jstl/neu" %>

<!-- Left side column. contains the sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->

        <div class="user-panel">
            <div class="pull-left image">
                <img style="width: 50px;height: 50px" src="${pageContext.request.contextPath}/static/style/pic/${user.head}" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${user.name}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->
        <!--<form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form> -->
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">MAIN NAVIGATION</li>

            <c:forEach items="${applicationScope.leftMenus}" var="parentMenu">

                <neu:menu menuName="${parentMenu.name}">

                <li class="treeview">
                    <a href="#">
                        <i class="${parentMenu.icon}"></i> <span>${parentMenu.name}</span>
                        <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <c:forEach items="${parentMenu.children}" var="childMenu">

                            <neu:menu menuName="${childMenu.name}">
                            <li><a href="${childMenu.url}"><i class="${childMenu.icon}"></i>${childMenu.name}</a></li>
                            </neu:menu>

                        </c:forEach>
                    </ul>
                </li>
                </neu:menu>
            </c:forEach>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>

