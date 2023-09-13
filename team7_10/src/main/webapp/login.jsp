<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SoundPlay | 로그인</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="apple-touch-icon" href="assets/img/apple-icon.png" />
<link rel="shortcut icon" type="image/x-icon"
	href="assets/img/logo-no-background.ico" />
<!-- Google API를 사용하기 위한 스크립트 -->
<script src="https://accounts.google.com/gsi/client" async defer>
	//Google api
</script>
<link rel="stylesheet" href="assets/css/login.css" />
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/css/templatemo.css" />
<link rel="stylesheet" href="assets/css/custom.css" />
<!-- 쿠팡로그인 따라하기-->
<script src="javascript/javascript.js"></script>
<!-- 쿠팡로그인 동작 따라하기 -->

<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap" />
<link rel="stylesheet" href="assets/css/fontawesome.min.css" />





</head>

<body>
	<!-- Start Top Nav -->
	<slk:nav />
	<!-- Close Top Nav -->

	<!-- Header -->
	<slk:header />
	<!-- Close Header -->

	<!-- Modal -->
	<slk:modal />

	<!-- Start Content Page -->
	<div class="container-fluid bg-dark py-5 text-light">
		<div class="col-md-6 m-auto text-center">
			<h1 class="h1">로 그 인</h1>
		</div>
	</div>

	<!-- 로그인 시작 -->
	<br>
	<div class="col-md-6 m-auto text-center">
		<main>
			<div id="loginbar">
				<form action="login.do" method="post" id="loginform">
					<fieldset>
						<legend class="skip">로그인</legend>
						<ul>
							<li>
							<span class="id_bg">
								<i class="fa fa-fw fa-user text-dark mr-3"></i>
							</span>
							<span>
								<input type="text" name="memberId" placeholder="아이디" required/>
							</span>
							</li>
							<li>
							<span class="pw_bg">
								<i class="fa fa-fw fa-lock text-dark mr-3"></i> <!-- 비밀번호 로고-->
							</span>
							<span>
								<input type="password" name="memberPw" placeholder="비밀번호" required/>
							</span> <!-- <span class="pw_show_hide" title="문자보이기"></span> -->
							</li>
						</ul>
						<button class="btn btn-success btn-lg px-3" type="submit"
							id="login_btn">로그인</button>
					</fieldset>
				</form>
				<a href="signupPage.do" class="join_link">회원가입</a>
			</div>
			<!-- 로그인 끝 -->
			<div class="snslogin">
				<!-- 구글 로그인 버튼 -->
				<div class="google_login">
					<div id="g_id_onload"
						data-client_id="378847199952-9f3b0pubnq9lcevarpdng410rkbjdvon.apps.googleusercontent.com"
						data-callback="handleCredentialResponse"></div>
					<div class="g_id_signin" data-type="standard" data-size="large"
						data-text="signin_with" data-shape="rectangular" data-width=185></div>
				</div>
			</div>
		</main>
	</div>
	<script>
		// ---[ 구글 로그인 ]-------------------------------------------------------------------------------------------------------------------------------------------------
		function handleCredentialResponse(response) {
			// decodeJwtResponse() is a custom function defined by you
			// to decode the credential response.
			const responsePayload = parseJwt(response.credential);
			
			
			
			console.log("구글 ID : " + responsePayload.sub);
			console.log("구글 Email : " + responsePayload.email);
			console.log("구글 Name : " + responsePayload.name);
			console.log("구글 프로필 사진 URL: " + responsePayload.picture);
			
			location.href='signup.do?memberId=G@'+responsePayload.sub
				+'&memberPw='+responsePayload.sub
				+'&memberName='+responsePayload.name
				+'&email='+responsePayload.email
				+'&phoneNumber=01000000000';
			// 비동기 아님..!
			
			/* $.ajax({
				url: 'signup.do?memberId=G@'+responsePayload.sub
						+'&memberPw='+responsePayload.sub
						+'&memberName='+responsePayload.name
						+'&email='+responsePayload.email
						+'&phoneNumber=01000000000',
				type:'POST',
				succes: function(){
					console.log("ajax 후 로그인성공");
					location.href="/main.do";
				},
				error:function(){
					console.log("ajax 후 로그인실패")
				}
			}); */
 	

			$('#status').append(responsePayload.name);
			//$('#status').append('<li>ID: ' + responsePayload.sub + '</li>');
			//$('#status').append('<li>Email: ' + responsePayload.email + '</li>');
			//$('#status').append('<li>Name: ' + responsePayload.name + '</li>');
			//$('#status').append('<li>Image: <img src=" ' + responsePayload.picture + '"></li>');
			
			
		};

		function parseJwt(token) {
			var base64Url = token.split('.')[1];
			var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
			var jsonPayload = decodeURIComponent(atob(base64).split('').map(
					function(c) {
						return '%'
								+ ('00' + c.charCodeAt(0).toString(16))
										.slice(-2);
					}).join(''));
			return JSON.parse(jsonPayload);
		};
		// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
	</script>
	<!-- Footer 내용을 이곳에 추가하세요 -->
	<slk:footer />



	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<script src="assets/js/javascript.js"></script>
	<!-- End Script -->
</body>
</html>
