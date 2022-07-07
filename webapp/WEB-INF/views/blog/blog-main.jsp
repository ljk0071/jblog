<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<!-- 기본이미지 -->
					<img id="proImg" src="${pageContext.request.contextPath}/upload/${bVo.saveName}">
					
					<!-- 사용자업로드 이미지 -->
					<%-- <img id="proImg" src=""> --%>
					
					<div id="nick">${bVo.name}(${bVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${cateInfoList}" var="cate">
							<li data-cateno="${cate.cateNo}">${cate.cateName}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
			<c:choose>
				<c:when test="${pVo != null}">
					<div id='postBox' class='clearfix'>
						<div id='postTitle' class='text-left'><strong>${pVo.postTitle}</strong></div>
						<div id='postDate' class='text-left'><strong>${pVo.regDate}</strong></div>
						<div id='postNick'>${bVo.name}(${bVo.id})</div>
					</div>
					<div id='post'>
						${pVo.postContent}
					</div>
				</c:when>		
				<c:otherwise>
				<!-- 글이 없는 경우 -->
					<div id="postBox" class="clearfix">
								<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
								<div id="postDate" class="text-left"><strong></strong></div>
								<div id="postNick"></div>
					</div>
				    
					<div id="post" >
					</div>
				</c:otherwise>
			</c:choose>
				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">

	$(document).ready(function() {
		fetchList();
	});
	
	function fetchList() {
		$.ajax({

			url : "${pageContext.request.contextPath}/${bVo.id}/postlist",
			type : "post",

			dataType : "json",
			success : function(pList) {
				for (var i = 0; i < pList.length; i++) {
					postListRender(pList[i]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	$("#cateList").on("click", "li", function() {
		$("#listTitle tr").remove();
		var $this = $(this);
		var cateNo = $this.data("cateno");
		$.ajax({

			url : "${pageContext.request.contextPath}/${bVo.id}/cate",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(cateNo),

			dataType : "json",
			success : function(pList) {
				for (var i = 0; i < pList.length; i++) {
					postListRender(pList[i]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	$("#listTitle").on("click", "td", function() {
		$("#postBox").remove();
		$("#post").remove();
		var $this = $(this);
		var cateNo = $this.data("cateno");
		var pTitle = $this.text();
		var pVo = {}
		pVo.cateNo = cateNo;
		pVo.postTitle = pTitle;
		$.ajax({

			url : "${pageContext.request.contextPath}/${bVo.id}/post",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(pVo),

			dataType : "json",
			success : function(pVo) {
					postContentRender(pVo);
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	function postListRender(pVo) {
		var str = "";
		str += "	<table>";
		str += "		<colgroup>";
		str += "			<col style=''>";
		str += "			<col style='width: 20%;'>";
		str += "		</colgroup>";
		str += "		<tr>";
		str += "			<td class='text-left' data-cateno='"+pVo.cateNo+"'>"+pVo.postTitle+"</td>";
		str += "			<td class='text-right'>"+pVo.regDate+"</td>";
		str += "		</tr>";
		str += "	</table>";
		
		$("#listTitle").append(str);
	}
	
	function postContentRender(pVo) {
		var str = "";
		str += "	<div id='postBox' class='clearfix'>";
		str += "		<div id='postTitle' class='text-left'><strong>"+pVo.postTitle+"</strong></div>";
		str += "		<div id='postDate' class='text-left'><strong>"+pVo.regDate+"</strong></div>";
		str += "		<div id='postNick'>${bVo.name}(${bVo.id})</div>";
		str += "	</div>";
		str += "	<div id='post'>";
		str += "		"+pVo.postContent+"";
		str += "	</div>";
		
		$("#post_area").prepend(str);
	}
	
</script>
</html>