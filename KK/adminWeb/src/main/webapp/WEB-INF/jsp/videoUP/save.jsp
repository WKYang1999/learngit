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
            <h1>${empty videoVO.id ? '添加':'编辑'}</h1>
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
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/videos/myVideo/${videoVO.id}" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="_method" value="${empty videoVO.id ? 'post':'put'}">
                            <input type="hidden" name="uploadId" value="${user.id}">
                            <div class="box-body">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="videoName" class="col-sm-2 control-label">名称</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="videoName" name="videoName"  class="form-control" placeholder="名称" value="${videoVO.videoName}" data-bv-notempty="true"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="brief" class="col-sm-2 control-label">简介</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="brief" name="brief"  class="form-control" placeholder="简介" value="${videoVO.brief}" data-bv-notempty="true"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="primary" class="col-sm-2 control-label">一级分类</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" id="primary" name="primary">
                                                <option value="1">动漫</option>
                                                <option value="2">电影</option>
                                                <option value="3">纪录片</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="secondary" class="col-sm-2 control-label">二级分类</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="secondary" name="secondary"  class="form-control" placeholder="二级分类" value="${videoVO.secondary}" data-bv-notempty="true"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="author" class="col-sm-2 control-label">作者</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="author" name="author"  class="form-control" placeholder="作者" value="${videoVO.author}" data-bv-notempty="true"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="file" class="col-sm-2 control-label">封面</label>
                                        <div class="col-sm-10">
                                            <input type="file" id="file" name="file"  class="form-control" placeholder="封面" data-bv-notempty="true"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="reset" class="btn btn-default btn-flat">重 置</button>
                                <button type="submit" class="btn btn-success btn-flat">${empty videoVO.id ? '添加':'编辑'}</button>
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
