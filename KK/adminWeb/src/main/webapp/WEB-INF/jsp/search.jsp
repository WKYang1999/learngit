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

		<div class="search">
			<h1><img src="${pageContext.request.contextPath}/static/style/pic/0.jpg" width="150px" alt=""></h1>
			<div class="middle">
				<div class="content">
					<form id="form_search" action="${pageContext.request.contextPath}/search" method="post" target="_blank" enctype="multipart/form-data">
						<input type="hidden" name="_method" value="post">
						<input type="text" id="videoName" name="videoName" value=""/>
						<button type="submit" style="width: 80px;height: 40px;background-color: red">搜索</button>
					</form>
				</div>
			</div>
		</div>
		<div class="nav">
			<h2>全部分类</h2>
			<ul>
				<li class="active"><a href="${applicationScope.notices}/index">首页</a></li>
				<li><a href="${applicationScope.notices}/1/ss">电影</a></li>
				<li><a href="${applicationScope.notices}/2/ss">动漫</a></li>
				<li><a href="${applicationScope.notices}/3/ss">纪录片</a></li>
			</ul>
		</div>
	</div>

		<div class="new" style="height: 624px;min-height: 640px;;width: 1100px;margin-top: 130px">
			<h2 style="font-family: 楷体;font-size: 20px">搜索结果</h2>
			<div class="sidebar-left" style="width: 1100px;min-height: 640px">
				<ul class="sidebar-left-ul">
					<c:forEach items="${search}" var="search">
						<li>
							<a href="${pageContext.request.contextPath}/videos/${search.id}/sp" target="_blank">
								<img src="${pageContext.request.contextPath}/static/style/pic/${search.pic}"/>
								<p>${search.videoName}</p>
								<p><span>${search.secondary}</span>&nbsp;&nbsp;<span>${search.score}</span></p>
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</body>
</html>
