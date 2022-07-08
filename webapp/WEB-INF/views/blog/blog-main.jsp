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
					<img id="proImg" src="${pageContext.request.contextPath}/upload/${bMap.bVo.saveName}">
					
					<!-- 사용자업로드 이미지 -->
					<%-- <img id="proImg" src=""> --%>
					
					<div id="nick">${bMap.bVo.name}(${bMap.bVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${bMap.cateInfoList}" var="cate">
							<li data-cateno="${cate.cateNo}">${cate.cateName}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
			<c:choose>
				<c:when test="${bMap.pVo != null}">
					<div id='postBox' class='clearfix'>
						<div id='postTitle' class='text-left'><strong>${bMap.pVo.postTitle}</strong></div>
						<div id='postDate' class='text-left'><strong>${bMap.pVo.regDate}</strong></div>
						<div id='postNick'>${bMap.bVo.name}(${bMap.bVo.id})</div>
					</div>
					<div id='post'>
						${bMap.pVo.postContent}
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
				<div id="comment">
					<table style="height: 30px;">
						<colgroup>
							<col style="width: 100px;">
							<col style="width: 590px;">
							<col style="width: 100px">
						</colgroup>
						<tr>
							<td class="userId" data-postno="${bMap.pVo.postNo}" data-userno="${authUser.userNo}">${authUser.name}</td>
							<td><input type="text" id="cmtContent" value="" style="width:590px;height:25px;"></td>
							<c:if test="${authUser != null}">
								<td><button id="cmtBtn" style="width:100px;height:30px;">저장</button></td>
							</c:if>
						</tr>
					</table>
				</div>
				<div id="cmtList">
					<c:forEach items="${bMap.cmtList}" var="cmt">
						<table id='cmt${cmt.cmtNo}' style="height: 30px;">
							<colgroup>
								<col style='width: 80px;'>
								<col style='width: 540px;'>
								<col style='width: 110px;'>
								<col style='width: 60px;'>
							</colgroup>
							<tr>
								<td>${cmt.userName}</td>
								<td>${cmt.cmtContent}</td>
								<td>${cmt.regDate}</td>
							<c:if test='${authUser.userNo == cmt.userNo}'>
								<td><img data-cmtno='${cmt.cmtNo}' class='btnCmtDel' src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>
							</c:if>
							</tr>
						</table>
					</c:forEach>
				</div>
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<c:forEach items="${bMap.pList}" var="pVo">
						<table>
							<colgroup>
							<col style=''>
							<col style='width: 20%;'>
							</colgroup>
							<tr>
							<td class='text-left' data-cateno='"+pVo.cateNo+"'>${pVo.postTitle}</td>
							<td class='text-right'>${pVo.regDate}</td>
							</tr>
						</table>
					</c:forEach>
				</div>
				<div id="paging" style="margin: 0px 0px 0px 350px;">
					<ul>
						<c:if test="${bMap.prev == true}">
							<li style="float:left;margin: 0px 8px 0px 8px;"> <a href="${pageContext.request.contextPath}/${bMap.bVo.id}?crtPage=${bMap.startPageNum-pMap.pageBtnCount}">◀</a></li>
						</c:if>
						<c:forEach begin="${bMap.startPageNum}" end="${bMap.endPageNum}" step="1" var="page">
							<c:choose>
								<c:when test="${bMap.crtPage==page}">
									<li style="float:left;margin: 0px 8px 0px 8px;"><strong><a href="${pageContext.request.contextPath}//${bMap.bVo.id}?crtPage=${page}">${page}</a></strong></li>
								</c:when>
								<c:otherwise>
									<li style="float:left;margin: 0px 8px 0px 8px;"><a href="${pageContext.request.contextPath}/${bMap.bVo.id}?crtPage=${page}">${page}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${bMap.next == true}">
							<li style="float:left;margin: 0px 40px 0px 40px;"><a href="${pageContext.request.contextPath}/${bMap.bVo.id}?crtPage=${bMap.endPageNum+1}">▶</a></li>
						</c:if>
					</ul>
				<div class="clear"></div>
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
// 		fetchList();
	});
	
// 	function fetchList() {
// 		$.ajax({

// 			url : "${pageContext.request.contextPath}/${bVo.id}/postList",
// 			type : "post",

// 			dataType : "json",
// 			success : function(pList) {
// 				for (var i = 0; i < pList.length; i++) {
// 					postListRender(pList[i]);
// 				}
// 			},
// 			error : function(XHR, status, error) {
// 				console.error(status + " : " + error);
// 			}
// 		});
// 	}
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
	
	function cmtListRender(cmtVo, opt) {
		var str = "";
		str += "	<table id='cmt"+cmtVo.cmtNo+"' style='height: 30px;'>";
		str += "		<colgroup>";
		str += "			<col style='width: 80px;''>";
		str += "			<col style='width: 540px;''>";
		str += "			<col style='width: 110px;''>";
		str += "			<col style='width: 60px;''>";
		str += "		</colgroup>";
		str += "		<tr>";
		str += "			<td>"+cmtVo.userName+"</td>";
		str += "			<td>"+cmtVo.cmtContent+"</td>";
		str += "			<td>"+cmtVo.regDate+"</td>";
		str += "			<td><img data-cmtno='"+ cmtVo.cmtNo +"' class='btnCmtDel' src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>";
		str += "		</tr>";
		str += "	</table>";
		if (opt == "down") {
			$("#cmtList").append(str);	
		}else if(opt == "up") {
			$("#cmtList").prepend(str);
		}
	}
	
	function postContentRender(pVo) {
		var str = "";
		str += "	<div id='postBox' class='clearfix'>";
		str += "		<div data-postno='"+pVo.postNo+"' id='postTitle' class='text-left'><strong>"+pVo.postTitle+"</strong></div>";
		str += "		<div id='postDate' class='text-left'><strong>"+pVo.regDate+"</strong></div>";
		str += "		<div id='postNick'>${bVo.name}(${bVo.id})</div>";
		str += "	</div>";
		str += "	<div id='post'>";
		str += "		"+pVo.postContent+"";
		str += "	</div>";
		
		$("#post_area").prepend(str);
	}
	
	
	function fetchCmtList() {
		$.ajax({

			url : "${pageContext.request.contextPath}/${bVo.id}/cmtlist",
			type : "post",

			dataType : "json",
			success : function(cmtList) {
				for (var i = 0; i < cmtList.length; i++) {
					cmtListRender(cmtList[i], "down");
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
	$("#cmtBtn").on("click", function() {
		var userNo = $(".userId").data("userno");
		var cmtContent = $("#cmtContent").val();
		var postNo = $(".userId").data("postno");
		var cmtVo = {}
		cmtVo.userNo = userNo;
		cmtVo.cmtContent = cmtContent;
		cmtVo.postNo = postNo;
		console.log(cmtVo)
		$.ajax({

			url : "${pageContext.request.contextPath}/${bVo.id}/comment",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(cmtVo),

			dataType : "json",
			success : function(cmtVo) {
					cmtListRender(cmtVo, "up");
					$("#cmtContent").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	$("#cmtList").on("click", ".btnCmtDel", function() {
		var $this = $(this);
		var cmtNo = $this.data("cmtno");
		console.log(cmtNo);
		$.ajax({
	
			url : "${pageContext.request.contextPath}/${bVo.id}/cmt/delete",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(cmtNo),
	
			dataType : "json",
			success : function() {
				$("#cmt"+cmtNo).remove();
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}//
		});
	})
</script>
</html>