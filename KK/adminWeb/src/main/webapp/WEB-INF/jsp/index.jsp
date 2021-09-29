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
<body>

<div class="header">

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
				<li>
				<a href="${pageContext.request.contextPath}/core" target="_blank">个人中心</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/recomms/${user.id}/cc" target="_blank">好友动态</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/invalidate">退出登录</a>
				</li>
			</c:if>
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

<c:if test="${aaa != null}">
	<div class="carousel" style="height: 300px;overflow-y: auto">
		<h2 style="font-family: 楷体;font-size: 20px">最新公告</h2>
		<ul>
			<c:forEach items="${notices}" var="notices" varStatus="index">
				<li style="font-size: 14px;font-family: 楷体;margin-left: 20px;margin-top: 10px">
						${index.count}.${notices.title}：${notices.notice}
				</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<c:if test="${bbb != null}">
	<div class="new" style="background: url(${pageContext.request.contextPath}/static/style/pic/67.jpg) no-repeat;height: 330px;width: 1210px">
		<h2 style="font-family: 楷体;font-size: 20px">热门推荐</h2>
		<div class="sidebar-left" style="width: 1100px;height: 300px;margin-left: 50px">
			<ul class="sidebar-left-ul">
				<c:forEach items="${hots}" var="hots">
					<li>
						<a href="${pageContext.request.contextPath}/videos/${hots.id}/sp" target="_blank">
							<img src="${pageContext.request.contextPath}/static/style/pic/${hots.pic}"/>
							<p>${hots.videoName}</p>
							<p><span>${hots.secondary}</span>&nbsp;&nbsp;点击量：<span>${hots.clickNum}</span></p>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>

<c:if test="${ccc != null}">
	<div class="new" style="height: 624px;background: url(${pageContext.request.contextPath}/static/style/pic/67.jpg) no-repeat;background-size: 1210px 640px">
		<h2 style="font-family: 楷体;font-size: 20px">最新动漫</h2>
		<div class="sidebar-left" style="overflow-y: auto">
			<ul class="sidebar-left-ul">
				<c:forEach items="${comic}" var="dm">
					<li>
						<a href="${pageContext.request.contextPath}/videos/${dm.id}/sp" target="_blank">
							<img src="${pageContext.request.contextPath}/static/style/pic/${dm.pic}"/>
							<p>${dm.videoName}</p>
							<p><span>${dm.secondary}</span></p>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="sidebar-right" style="overflow-y: auto">
			<p>排行榜</p>
			<ul class="sidebar-right-ul">
				<c:forEach items="${comic}" var="dm" varStatus="index">
					<li><a href="${pageContext.request.contextPath}/videos/${dm.id}/sp" target="_blank">${index.count}.${dm.videoName}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>

<c:if test="${ddd != null}">
	<div class="new" style="height: 624px;background: url(${pageContext.request.contextPath}/static/style/pic/67.jpg) no-repeat;background-size: 1210px 640px">
		<h2 style="font-family: 楷体;font-size: 20px">最新电影</h2>
		<div class="sidebar-left" style="overflow-y: auto">
			<ul class="sidebar-left-ul">
				<c:forEach items="${film}" var="dy">
					<li>
						<a href="${pageContext.request.contextPath}/videos/${dy.id}/sp" target="_blank">
							<img src="${pageContext.request.contextPath}/static/style/pic/${dy.pic}"/>
							<p>${dy.videoName}</p>
							<p><span>${dy.secondary}</span></p>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="sidebar-right" style="overflow-y: auto">
			<p>排行榜</p>
			<ul class="sidebar-right-ul">
				<c:forEach items="${film}" var="dy" varStatus="index">
					<li><a href="${pageContext.request.contextPath}/videos/${dy.id}/sp" target="_blank">${index.count}.${dy.videoName}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>

<c:if test="${eee != null}">
	<div class="new" style="height: 624px;background: url(${pageContext.request.contextPath}/static/style/pic/67.jpg) no-repeat;background-size: 1210px 640px">
		<h2 style="font-family: 楷体;font-size: 20px">最新纪录片</h2>
		<div class="sidebar-left" style="overflow-y: auto">
			<ul class="sidebar-left-ul">
				<c:forEach items="${documentary}" var="jlp">
					<li>
						<a href="${pageContext.request.contextPath}/videos/${jlp.id}/sp" target="_blank">
							<img src="${pageContext.request.contextPath}/static/style/pic/${jlp.pic}"/>
							<p>${jlp.videoName}</p>
							<p><span>${jlp.secondary}</span></p>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="sidebar-right" style="overflow-y: auto">
			<p>排行榜</p>
			<ul class="sidebar-right-ul">
				<c:forEach items="${documentary}" var="jlp" varStatus="index">
					<li><a href="${pageContext.request.contextPath}/videos/${jlp.id}/sp" target="_blank">${index.count}.${jlp.videoName}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>

<div class="content">
</div>
<div class="favorite">
</div>
<div class="history">
</div>
<div class="footer">
</div>
</body>
</html>
