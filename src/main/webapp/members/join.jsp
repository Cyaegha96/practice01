<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
.card {
	max-width: 550px;
	margin: 50px auto;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	border-radius: 12px;
}

.card-header {
	font-size: 1.5rem;
	font-weight: bold;
	text-align: center;
}
</style>
</head>
<body
	style="background-image: url('https://picsum.photos/1920/1080'); background-size: cover; background-position: center;">

	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white">회원가입</div>
			<div class="card-body">
				<form action="signup.members" method="post">
					<div class="mb-3 row">
						<label for="id" class="col-sm-3 col-form-label">아이디</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="id"
								placeholder="아이디 입력" name="id">
						</div>
						<div class="col-sm-3">
							<button type="button" class="btn btn-secondary w-100"
								id="dupcheck">중복확인</button>
						</div>
					</div>

					<div class="mb-3 row">
						<label for="pw" class="col-sm-3 col-form-label">비밀번호</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" id="pw"
								placeholder="비밀번호 입력" name="pw">
						</div>
					</div>

					<div class="mb-3 row">
						<label for="repw" class="col-sm-3 col-form-label">비밀번호 확인</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" id="repw"
								placeholder="비밀번호 재입력" name="repw">
						</div>
					</div>

					<div class="mb-3 row">
						<label for="name" class="col-sm-3 col-form-label">이름</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="name"
								placeholder="이름 입력" name="name">
						</div>
					</div>

					<div class="mb-3 row">
						<label for="phone" class="col-sm-3 col-form-label">전화번호</label>
						<div class="col-sm-9">
							<input type="tel" class="form-control" id="phone"
								placeholder="010-1234-5678" name="phone">
						</div>
					</div>

					<div class="mb-3 row">
						<label for="email" class="col-sm-3 col-form-label">이메일</label>
						<div class="col-sm-9">
							<input type="email" class="form-control" id="email"
								placeholder="이메일 입력" name="email">
						</div>
					</div>

					<div class="mb-3 row">
						<label for="zipcode" class="col-sm-3 col-form-label">우편번호</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="zipcode"
								name="zipcode" readonly>
						</div>
						<div class="col-sm-3">
							<button type="button" class="btn btn-outline-primary w-100"
								id="postSearchBtn">찾기</button>
						</div>
					</div>

					<div class="mb-3 row">
						<label for="address1" class="col-sm-3 col-form-label">주소</label>
						<div class="col-sm-9">
							<input type="text" class="form-control mb-2" id="address1"
								placeholder="도로명 주소" name="address1"> <input type="text"
								class="form-control" id="address2" placeholder="상세주소"
								name="address2">
						</div>
					</div>

					<div class="d-flex justify-content-between">
						<button type="button" class="btn btn-secondary">초기화면</button>
						<button type="submit" class="btn btn-success">회원가입</button>
						<button type="button" class="btn btn-danger" id="reload">다시입력</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script>
		$("#dupcheck").on("click", function() {
			let regexId = /^(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{6,12}$/g;
			let inspectId = regexId.test($("#id").val());
			if (!inspectId) {
				alert("아이디 형식이 다릅니다.");
				$("#id").val("");
				return false;
			} else {
				console.log($("#id").val());
					
				$.ajax({
					url : "/idcheck.members",
					data : {
						id : $("#id").val()
					}
				}).done(function(resp) {

					$("#resp").append(resp);
				});

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
							} else if (!(inspectrePw == $("#pw").val())) {
								alert("비밀번호가 일치하지 않습니다.");
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
		$("#reload").on("click", function() {

			location.reload();

		})
	</script>
</body>
</html>
