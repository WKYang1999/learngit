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
            <h1>${empty videoNumVOs.id ? '添加':'编辑'}</h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>系统管理</a></li>
                <li><a href="#">角色管理</a></li>
                <li class="active">列表</li>
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
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/videoNums/myVideo/${videoNumVOs.id}" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="_method" value="${empty videoNumVOs.id ? 'post':'put'}">
                            <div class="box-body">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="numId" class="col-sm-2 control-label">序号</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="numId" name="numId"  class="form-control" placeholder="序号" value="${videoNumVOs.numId}" data-bv-notempty="true"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="videoId" class="col-sm-2 control-label">视频名称</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" id="videoId" name="videoId">
                                                <option value="">请选择</option>
                                                <c:forEach items="${videos}" var="video">
                                                    <option value="${video.id}">${video.videoName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name" class="col-sm-2 control-label">标题</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="name" name="name"  class="form-control" placeholder="标题" value="${videoNumVOs.name}" data-bv-notempty="true"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="file" class="col-sm-2 control-label">视频</label>
                                        <div class="col-sm-10">
                                            <input type="file" id="file" name="file"  class="form-control" placeholder="视频" value="${videoNumVOs.sp}" data-bv-notempty="true"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="reset" class="btn btn-default btn-flat">重 置</button>
                                <button type="submit" class="btn btn-success btn-flat">${empty videoNumVOs.id ? '添加':'编辑'}</button>
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
