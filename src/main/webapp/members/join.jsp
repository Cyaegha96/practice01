<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>

<style>
* {
	box-sizing: border-box;
}

.container {
	border: 1px solid blue;
	margin: auto;
	width: 500px;
	height: 500px;
	width: 500px
}

.inputform {
	width: 100%;
	height: 90%;
}

.btnform {
	width: 100%;
	height: 10%;
	margin: auto;
}

.topbar {
	text-align: center;
}

div {
	margin: auto;
}
</style>


</head>
<body>
	<form action="" method="post">
	<div class="container">
		<div class="row inputform">
			<div class="col-12 topbar">회원가입</div>
			<div class="col-3">아이디</div>
			<div class="col-5">
				<input type="text" placeholder="아이디 입력" id="id">
			</div>
			<div class="col-4">
				<button type="button" id="dupcheck">중복확인</button>
			</div>
			<div class="col-3">비밀번호</div>
			<div class="col-9">
				<input type="text" placeholder="비밀번호 입력" id="pw">
			</div>
			<div class="col-3">비밀번호 확인</div>
			<div class="col-9">
				<input type="text" placeholder="비밀번호 입력" id="repw">
			</div>
			<div class="col-3">이름</div>
			<div class="col-9">
				<input type="text" placeholder="이름 입력" id="name">
			</div>
			<div class="col-3">전화번호</div>
			<div class="col-9">
				<input type="text" placeholder="전화번호 입력" id="phone">
			</div>
			<div class="col-3">이메일</div>
			<div class="col-9">
				<input type="text" placeholder="이메일 입력" id="email">
			</div>
			<div class="col-3">우편번호</div>
			<div class="col-5">
				<input type="text" readonly id="zipcode">
			</div>
			<div class="col-4">
				<button type="button" id="postSearchBtn">우편번호찾기</button>
			</div>
			<div class="col-3">상세주소</div>
			<div class="col-9">
				<input type="text" id="address1">
			</div>
			<div class="col-3"></div>
			<div class="col-9">
				<input type="text" id="address2">
			</div>

		</div>
		<div class="row btnform">
			<div class="col-4">
				<button type="button">초기화면</button>
			</div>
			<div class="col-4">
				<button type="submit">회원가입</button>
			</div>
			<div class="col-4">
				<button type="button" id="reload">다시입력</button>
			</div>
		</div>


	</div>
</form>

	<script>
		$("#dupcheck").on("click", function() {
			let regexId = /^(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{6,12}$/g;
			let inspectId = regexId.test($("#id").val());
			if (!inspectId) {
				alert("아이디 형식이 다릅니다.");
				$("#id").val("");
				return false;
			} else {
				alert("성공");
				//$("#resp").html("");
				//$.ajax({
				//	url : "/idcheck.members",
				//	data : {
				//		id : $("#id").val()
				//	}
				//}).done(function(resp) {
//
				//	$("#resp").append(resp);
				//});

			}

		});
		$("form")
		.on(
				"submit",
				function() {

					let regexPw = /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/g;
					let inspectPw = regexPw.test($("#pw").val());
					let inspectrePw = $("#repw").val();
					let regexName = /^[가-힣]{2,6}$/g;
					let inspectName = regexName.test($("#name").val());
					let regexPhone = /(010-?\d{4}-?\d{4})/g;
					let inspectPhone = regexPhone.test($("#phone")
							.val());
					let regexEmail = /\w+@{1}[a-z]+\.[a-z]+\.?[a-z]*\.?[a-z]*\.?[a-z]*$/g;
					let inspectEmail = regexEmail.test($("#email")
							.val());

					if (!inspectPw) {
						alert("비밀번호 형식이 다릅니다.");
						return false;
					} else if (!inspectName) {
						alert("이름 형식이 다릅니다.");
						return false;
					} else if (!inspectPhone) {
						alert("연락처 형식이 다릅니다.");

						return false;
					} else if (!inspectEmail) {
						alert("이메일 형식이 다릅니다.");
						return false;
					} else if (!(inspectrePw == $("#pw").val())) {
						alert("비밀번호가 일치하지 않습니다.");
						return false;
					} else {
						alert("성공");

					}

				});
		
		

		let btn = document.getElementById("postSearchBtn");

		btn.onclick = function() {
			new daum.Postcode({
				oncomplete : function(data) {
					
					 let zipcode = document.getElementById("zipcode");
		               zipcode.value = data.zonecode;
		               let address1 = document.getElementById("address1");
		               address1.value = data.roadAddress;
				}
			}).open();
			
		}
		$("#reload").on("click",function(){
			
			location.reload();
			
		})
		
		
		
	</script>


</body>
</html>