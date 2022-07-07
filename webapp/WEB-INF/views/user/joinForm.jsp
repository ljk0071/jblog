<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>

		<div>		
			<div id="joinForm">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id" value=""></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="pw"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="name"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
			</div>
	      		
			
		</div>
		
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		
	</div>

</body>

<script type="text/javascript">

	var chk = 0;

	$("#btnJoin").on("click", function() {
		
		var id = $("[name='id']").val();
		var pw = $("[name='pw']").val();
		var name = $("[name='name']").val();
		var agree = $("#chkAgree").is(":checked");
		console.log(agree);
		var uVo = {}
		uVo.id = id;
		uVo.pw = pw;
		uVo.name = name;
		
		if ( id == "") {
			alert("아이디를 입력해 주세요.");
		} else if (chk != 1) {
			alert("아이디 중복검사 후 시도해주세요");
		} else if ( pw == "" ) {
			alert("비밀번호를 입력해 주세요.");
		} else if ( name == "" ) {
			alert("이름를 입력해 주세요.");
		} else if( agree == false ) {
			alert("약관에 동의해주세요.");
		} else if ( chk == 1 ) {
				$.ajax({
					
					url : "${pageContext.request.contextPath}/user/joinCheck",
					type : "post",
			 		contentType : "application/json",
					data : JSON.stringify(uVo),
			
					dataType : "json",
					success : function(cnt) {
						if (cnt == 1) {
							window.location.href = "${pageContext.request.contextPath}/user/joinSuccess";
						}
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
				});
			}
		}
	});

	$("#btnIdCheck").on("click", function() {

		var id = $("[name='id']").val();
		
		$.ajax({
			
			url : "${pageContext.request.contextPath}/user/idCheck",
			type : "post",
			contentType : "application/json; charset=UTF-8",
			data : JSON.stringify(id),
	
			dataType : "json",
			success : function(result) {
				if ( result == 1 ) {
					$("#tdMsg").text("사용할 수 있는 아이디 입니다.");
					return chk = 1;
				} else {
					$("#tdMsg").text("다른 아이디로 기입해 주세요.");
					return chk = -1;
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
</script>


</html>