<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Blank Page</title>
    <%@include file="/WEB-INF/jsp/common/base.jsp"%>
</head>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="../../index2.html"><b>KK视频</b>欢迎您</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">输入账号和密码即可完成注册</p>
        <form id="form_register" class="form-horizontal"  method="post">
            <div class="form-group has-feedback">
                <input type="text" name="name" class="form-control" placeholder="昵称">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="text" name="loginName" class="form-control" placeholder="账号">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <a href="${pageContext.request.contextPath}/login">已有账号！</a>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">注册</button>
                </div>
            </div>
        </form>
    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<script type="text/javascript">

    $("#form_register").on("submit",function(e){

        e.preventDefault();
        var $form = $(this);

        var name = $form.find("input[name='name']").val();
        var loginName = $form.find("input[name='loginName']").val();
        var password = $form.find("input[name='password']").val();

        //TODO 校验

        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/users/register",
            data:"name="+name+"&loginName="+loginName+"&password="+password,
            success:function(respData){
                if(respData.code!=200){
                    alert(respData.msg);
                }else{
                    alert(respData.msg);
                    window.location.href="${pageContext.request.contextPath}/login";
                }
            }
        });
        return false;
    });
</script>
</body>
</html>
