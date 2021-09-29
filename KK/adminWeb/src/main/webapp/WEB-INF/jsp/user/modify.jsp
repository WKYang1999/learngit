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
            <h1>修改密码</h1>
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
                        <form id="from_modify" class="form-horizontal" method="post">
                            <input type="hidden" name="id" value="${user.id}">
                            <input type="hidden" name="loginName" value="${user.loginName}">
                            <div class="box-body">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="password" class="col-sm-2 control-label">原密码</label>
                                        <div class="col-sm-10">
                                            <input type="password" id="password" name="password"  class="form-control" placeholder="原密码" value="" data-bv-notempty="true"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="newPassword" class="col-sm-2 control-label">新密码</label>
                                        <div class="col-sm-10">
                                            <input type="password" id="newPassword" name="newPassword"  class="form-control" placeholder="新密码" value="" data-bv-notempty="true"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="reset" class="btn btn-default btn-flat">重 置</button>
                                <button type="submit" class="btn btn-success btn-flat">修改密码</button>
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

    $("#from_modify").on("submit",function(e){

        e.preventDefault();
        var $form = $(this);

        var id = $form.find("input[name='id']").val();
        var loginName = $form.find("input[name='loginName']").val();
        var password = $form.find("input[name='password']").val();
        var newPassword = $form.find("input[name='newPassword']").val();

        //TODO 校验

        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/users/modify",
            data:"id="+id+"&loginName="+loginName+"&password="+password+"&newPassword="+newPassword,
            success:function(respData){
                if(respData.code!=200){
                    alert(respData.msg);
                }else{
                    alert(respData.msg);
                    window.location.href="${pageContext.request.contextPath}/invalidate";
                }
            }
        });
        return false;
    });
</script>
</body>
</html>
