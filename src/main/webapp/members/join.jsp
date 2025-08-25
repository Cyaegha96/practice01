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
.inputform{
	width : 100%;
	height: 90%;
}
.btnform{
	width : 100%;
	height: 10%;
	margin : auto;
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

	<div class="container">
		<div class="row inputform">
			<div class="col-12 topbar">회원가입</div>
			<div class="col-3">아이디</div>
			<div class="col-5">
				<input type="text" placeholder="아이디 입력">
			</div>
			<div class="col-4">
				<button>중복확인</button>
			</div>
			<div class="col-3">비밀번호</div>
			<div class="col-9">
				<input type="text" placeholder="비밀번호 입력">
			</div>
			<div class="col-3">이름</div>
			<div class="col-9">
				<input type="text" placeholder="이름 입력">
			</div>
			<div class="col-3">전화번호</div>
			<div class="col-9">
				<input type="text" placeholder="전화번호 입력">
			</div>
			<div class="col-3">이메일</div>
			<div class="col-9">
				<input type="text" placeholder="이메일 입력">
			</div>
			<div class="col-3">우편번호</div>
			<div class="col-5">
				<input type="text" readonly>
			</div>
			<div class="col-4">
				<button>우편번호찾기</button>
			</div>
			<div class="col-3">상세주소</div>
			<div class="col-9">
				<input type="text">
			</div>
			<div class="col-3"></div>
			<div class="col-9">
				<input type="text">
			</div>

		</div>
		<div class="row btnform">
		<div class="col-4"><button>초기화면</button></div>
		<div class="col-4"><button>회원가입</button></div>
		<div class="col-4"><button>다시입력</button></div>
		</div>


	</div>





</body>
</html>