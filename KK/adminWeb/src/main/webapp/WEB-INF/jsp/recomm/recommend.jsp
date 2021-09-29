<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="neu" uri="http://java.sun.com/jsp/jstl/neu" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>KK视频</title>
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/reset.css">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/index.css">
	</head>
	<body style="background: url(${pageContext.request.contextPath}/static/style/pic/67.jpg) no-repeat;background-size: 100% 800px;background-attachment: fixed;">

	<div class="header" style="height: 20px">
		<div class="top">
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

		<div class="new" style="height: 624px;min-height: 640px;;width: 1100px">
			<h2 style="font-family: 楷体;font-size: 20px">好友动态</h2>
			<div style="width: 1100px;min-height: 640px">
				<ul>
					<c:forEach items="${recom}" var="recom">
						<li style="width: 1000px;height: 100px;background: rgba(255, 255, 255, 0.5);margin-left: 20px;margin-top: 10px;font-size: 14px">
							<img src="${pageContext.request.contextPath}/static/style/pic/${recom.head}" style="width: 30px;height: 50px;float: left;"/><br/>
							&nbsp;&nbsp;&nbsp;&nbsp;${recom.userName}：
							和大家分享一个视频：
							<a href="${pageContext.request.contextPath}/videos/${recom.videoId}/sp" target="_blank">
								${recom.videoName}
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</body>
</html>
