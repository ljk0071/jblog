<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

</head>
<body>
	<div id="center-content">
		
		<!--메인 해더 자리 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		
		
		<form id="search-form">
			<fieldset>
				<input type="text" name="keyword" value="">
				<button id="btnSearch" type="submit" >검색</button>
			</fieldset>
			
			<fieldset>
				<label for="rdo-title">블로그 제목</label> 
				<input id="rdo-title" type="radio" name="kwdOpt" value="optTitle" checked="checked"> 
				
				<label for="rdo-userName">블로거 이름</label> 
				<input id="rdo-userName" type="radio" name="kwdOpt" value="optName" > 
			</fieldset>
		</form>
		
		<div id="resultList">
			<c:forEach items="${bMap.bList}" var="bVo">
			<table border="1" > 
				<tr>
					<td><img style="width:80px;height:80px;"src="${pageContext.request.contextPath}/upload/${bVo.saveName}"></td>
					<td style="width:420px;">${bVo.blogTitle}</td>
					<td style="width:200px;">${bVo.name}(${bVo.id})</td>
					<td style="width:300px;">${bVo.joinDate}</td>
				</tr>
			</table>
			</c:forEach>
		</div>
		
		<div id="paging" style="margin: 0px 0px 0px 350px;">
					<ul>
						<c:if test="${bMap.prev == true}">
							<li style="float:left;margin: 0px 8px 0px 8px;"> <a href="${pageContext.request.contextPath}/?keyword=${bMap.keyword}&kwdOpt=${bMap.kwdOpt}&crtPage=${bMap.startPageNum-pMap.pageBtnCount}">◀</a></li>
						</c:if>
						<c:forEach begin="${bMap.startPageNum}" end="${bMap.endPageNum}" step="1" var="page">
							<c:choose>
								<c:when test="${bMap.crtPage==page}">
									<li style="float:left;margin: 0px 8px 0px 8px;"><strong><a href="${pageContext.request.contextPath}/?keyword=${bMap.keyword}&kwdOpt=${bMap.kwdOpt}&crtPage=${page}">${page}</a></strong></li>
								</c:when>
								<c:otherwise>
									<li style="float:left;margin: 0px 8px 0px 8px;"><a href="${pageContext.request.contextPath}/?keyword=${bMap.keyword}&kwdOpt=${bMap.kwdOpt}&crtPage=${page}">${page}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${bMap.next == true}">
							<li style="float:left;margin: 0px 40px 0px 40px;"><a href="${pageContext.request.contextPath}/?keyword=${bMap.keyword}&kwdOpt=${bMap.kwdOpt}&crtPage=${bMap.endPageNum+1}">▶</a></li>
						</c:if>
					</ul>
				<div class="clear"></div>
			</div>
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
	
	
	</div>
	<!-- //center-content -->
</body>
</html>