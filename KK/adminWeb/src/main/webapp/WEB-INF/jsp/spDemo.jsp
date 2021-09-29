<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="neu" uri="http://java.sun.com/jsp/jstl/neu" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/video.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/index.css">
</head>
<body>

<div class="header" style="height: 20px">

	<div class="top" style="height: 20px">
		<ul>
			<c:if test="${user.name == null}">
				<li>
					您好！欢迎来到KK视频！
					<a href="${pageContext.request.contextPath}/login">[登录]</a>
					<a href="${pageContext.request.contextPath}/register">[免费注册]</a>
				</li></c:if>
			<c:if test="${user.name != null}">
				<li>
					<span>欢迎&nbsp; ${user.name}&nbsp; 来到KK视频</span>
				</li>
			</c:if>
			<li>
				<a href="${pageContext.request.contextPath}/core" target="_blank">个人中心</a>
			</li>
		</ul>
	</div>
</div>
<div class="sp" style="height: 700px">
	<span style="font-family: 楷体;font-size: 16px;margin-top: 10px;margin-left: 20px">第${gkNum.numId}集：${gkNum.name}&nbsp;&nbsp;&nbsp;上传时间：${gkNum.date}</span>
	<video style="margin-top: 20px" id="videoNum" src="${pageContext.request.contextPath}/static/style/MP4/${gkNum.sp}" controls autoplay="ture" width="1100px"></video>
</div>
<div class="sp" style="height: 50px">

</div>
</body>
</html>
