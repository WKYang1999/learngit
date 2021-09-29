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
            <h1>UP申请</h1>
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
                        <form id="form_apply" class="form-horizontal" method="post">
                            <input type="hidden" name="_method" value="post">
                            <input type="hidden" name="userId" value="${user.id}">
                            <div class="box-body">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name" class="col-sm-2 control-label">申请人</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="name" name="name"  class="form-control" placeholder="" value="${user.name}" data-bv-notempty="true"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="submit" class="btn btn-success btn-flat">申请</button>
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
<script type="text/javascript">

    $("#form_apply").on("submit",function(e){

        e.preventDefault();
        var $form = $(this);

        var userId = $form.find("input[name='userId']").val();

        //TODO 校验

        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/applys/add",
            data:"userId="+userId,
            success:function(respData){
                if(respData.code!=200){
                    alert(respData.msg);
                }else{
                    alert(respData.msg);
                    window.location.href="${pageContext.request.contextPath}/applys/save";
                }
            }
        });
        return false;
    });
</script>
</body>
</html>
