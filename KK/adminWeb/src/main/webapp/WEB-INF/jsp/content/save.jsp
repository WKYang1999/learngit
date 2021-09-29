<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Blank Page</title>

    <%@include file="/WEB-INF/jsp/common/base.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

     <!--头部-->
    <%@include file="/WEB-INF/jsp/common/top.jsp"%>


    <!--菜单-->
    <%@include file="/WEB-INF/jsp/common/left.jsp"%>


    <div class="content-wrapper">
        <section class="content-header">
            <h1>聊天</h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>系统管理</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <!-- right column -->
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <!-- form start -->
                        <div style="overflow-y: auto;height: 500px">
                            <ul style="list-style: none; padding: 0px;margin: 0px">
                                <c:forEach items="${lts}" var="lts">
                                    <li>
                                        &nbsp;&nbsp;${lts.name}&nbsp;&nbsp;${lts.date}<br/>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：${lts.content}
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/contents/add" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="_method" value="post">
                            <input type="hidden" name="yourId" value="${user.id}">
                            <input type="hidden" name="userId" value="${a}">
                            <div class="box-body">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="content" class="col-sm-2 control-label">发送内容</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="content" name="content"  class="form-control" placeholder="发送内容" value="" data-bv-notempty="true"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="reset" class="btn btn-default btn-flat">重 置</button>
                                <button type="submit" class="btn btn-success btn-flat">发送</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!--底部-->
    <%@include file="/WEB-INF/jsp/common/foot.jsp"%>

    <!--设置-->
    <%@include file="/WEB-INF/jsp/common/setting.jsp"%>
</div>
<!-- ./wrapper -->
</body>
</html>
