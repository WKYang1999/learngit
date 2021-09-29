<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="neu" uri="http://java.sun.com/jsp/jstl/neu" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>详情</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/video.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/index.css">
	<%@include file="/WEB-INF/jsp/common/base.jsp"%>
</head>
<body>

<div class="header" style="height: 30px">

	<div class="top" style="height: 30px">
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
</div>

<div class="details" style="border: 1px solid #000000;">
	<img src="${pageContext.request.contextPath}/static/style/pic/${videoDemo.pic}"/>
	<div class="c">
		<p>名  称：${videoDemo.videoName}</p>
		<p>作  者：${videoDemo.author}</p>
		<p>类  型：${videoDemo.primary}&nbsp;${videoDemo.secondary}</p>
		<p>详  情：${videoDemo.brief}</p>
		<p>访问量：${videoDemo.clickNum}&nbsp;&nbsp;
		<c:if test="${user.id != null}">
			<button id="collection">收藏</button>&nbsp;
			<button id="like">点赞</button>&nbsp;<button id="report">举报</button>&nbsp;<button id="recomm">分享至动态</button>
		</c:if></p>
	</div>
</div>
<div class="details" style="height: 50px;border: 1px solid #000000">
	<ul>
		<c:forEach items="${xx}" var="xx">
			<li style="float: left;margin-top: 15px;margin-right: 15px;background-color: cyan;"><a href="${pageContext.request.contextPath}/videoNums/${xx.videoId}/${xx.numId}/gk" target="_blank">${xx.numId}.${xx.name}</a></li>
		</c:forEach>
	</ul>
</div>
<div class="comment">
	<label>评论区:</label>&nbsp;&nbsp;&nbsp;
	<c:if test="${user.id != null}">
		<form id="form_comment" method="post">
			<input class="text" name="content" type="text" />&nbsp;&nbsp;&nbsp;
			<input type="hidden" id="videoId" name="videoId" value="${videoDemo.id}" />
			<input type="hidden" id="userId" name="userId" value="${user.id}" />
			<button type="submit" class="submit">发送</button>
		</form>
	</c:if>
	<ul>
		<c:forEach items="${comm}" var="comm">
			<li><p>评论人：${comm.name}&nbsp;&nbsp;时间：${comm.dateComm}
				<c:if test="${user.id == comm.userId}"><a href="${pageContext.request.contextPath}/comments/${comm.videoId}/${comm.id}/delete">删除</a></c:if>
				&nbsp;&nbsp;
				<c:if test="${user.id != null}">
					<c:if test="${user.id != comm.userId}">
						<a href="${pageContext.request.contextPath}/reportComms/${videoDemo.id}/${user.id}/${comm.id}">举报</a>
					</c:if>
				</c:if>
			<br/>:${comm.content}</p></li>
		</c:forEach>
	</ul>
</div>

<script type="text/javascript">

	$("#recomm").on("click",function () {
		var userId = document.getElementById("userId").value;
		var videoId = document.getElementById("videoId").value;
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/recomms/add",
			data:"userId="+userId+"&videoId="+videoId,
			success:function(respData){
				if(respData.code!=200){
					alert(respData.msg);
				}else{
					alert(respData.msg);
					window.location.href="${pageContext.request.contextPath}/videos/"+videoId+"/sp";
				}
			}
		});
		return false;
	});

	$("#report").on("click",function () {
		var userId = document.getElementById("userId").value;
		var videoId = document.getElementById("videoId").value;

		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/reportVideos/add",
			data:"userId="+userId+"&videoId="+videoId,
			success:function(respData){
				if(respData.code!=200){
					alert(respData.msg);
				}else{
					alert(respData.msg);
					window.location.href="${pageContext.request.contextPath}/videos/"+videoId+"/sp";
				}
			}
		});
		return false;
	});

	$("#like").on("click",function () {
		var userId = document.getElementById("userId").value;
		var videoId = document.getElementById("videoId").value;

		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/likes/add",
			data:"userId="+userId+"&videoId="+videoId,
			success:function(respData){
				if(respData.code!=200){
					alert(respData.msg);
				}else{
					alert(respData.msg);
					window.location.href="${pageContext.request.contextPath}/videos/"+videoId+"/sp";
				}
			}
		});
		return false;
	});

	$("#collection").on("click",function () {
		var userId = document.getElementById("userId").value;
		var videoId = document.getElementById("videoId").value;

		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/collections/add",
			data:"userId="+userId+"&videoId="+videoId,
			success:function(respData){
				if(respData.code!=200){
					alert(respData.msg);
				}else{
					alert(respData.msg);
					window.location.href="${pageContext.request.contextPath}/videos/"+videoId+"/sp";
				}
			}
		});
		return false;
	});

	$("#form_comment").on("submit",function(e){
		e.preventDefault();

		var $form = $(this);

		var userId = $form.find("input[name='userId']").val();
		var videoId = $form.find("input[name='videoId']").val();
		var content = $form.find("input[name='content']").val();

		//TODO 校验
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/comments/add",
			data:"userId="+userId+"&videoId="+videoId+"&content="+content,
			success:function(respData){
				if(respData.code!=200){
					alert(respData.msg);
				}else{
					alert(respData.msg);
					window.location.href="${pageContext.request.contextPath}/videos/"+videoId+"/sp";
				}
			}
		});
		return false;
	});
</script>
</body>
</html>
