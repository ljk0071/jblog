<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<c:choose>
		<c:when test="${authUser.id != bMap.bVo.id}">
			<c:import url="/WEB-INF/views/error/403.jsp"></c:import>
		</c:when>
	<c:otherwise>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${bMap.bVo.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${bMap.bVo.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${bMap.bVo.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
		      			
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->
	</c:otherwise>
	</c:choose>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		fetchList();
	});
	
	$("#btnAddCate").on("click", function() {
		var name = $("[name='name']").val();
		var desc = $("[name='desc']").val();
		var cVo = {}
		cVo.cateName = name;
		cVo.description = desc;
		
		$.ajax({

			url : "${pageContext.request.contextPath}/${bMap.bVo.id}/cateList/add",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(cVo),

			dataType : "json",
			success : function(update) {
				render(update, "up");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}//
		});
	})

	$("#cateList").on("click",".btnCateDel", function() {
		var $this = $(this);
		var cateNo = $this.data("cateno");
		$.ajax({

			url : "${pageContext.request.contextPath}/${bMap.bVo.id}/cateList/delete",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(cateNo),

			dataType : "json",
			success : function(result) {
				if ( result == 0 ) {
					alert("삭제 할 수 없습니다.")
				} else {
					$("#cate"+cateNo).remove();
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}//
		});
	})
	
	function fetchList() {
		$.ajax({

			url : "${pageContext.request.contextPath}/${bMap.bVo.id}/cateList",
			type : "post",

			dataType : "json",
			success : function(cList) {
				for (var i = 0; i < cList.length; i++) {
					render(cList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	function render(cVo, opt) {
		var str = "";
		str += "	<tr id='cate"+cVo.cateNo+"'>";
		str += "		<td>" + cVo.cateNo + "</td>";
		str += "		<td>" + cVo.cateName + "</td>";
		str += "		<td>" + cVo.count + "</td>";
		str += "		<td>" + cVo.description + "</td>";
		str += "		<td clas='text-center'>";
		str += "			<img data-cateNo='"+ cVo.cateNo +"' class='btnCateDel' src='${pageContext.request.contextPath}/assets/images/delete.jpg'>";
		str += "		</td>";
		str += "	</tr>";
		
		if (opt == "down") {
			$("#cateList").append(str);
		} else if (opt == "up") {
			$("#cateList").prepend(str);
		} else {
			console.log("opt오류");
		}
	}
	
</script>




</html>