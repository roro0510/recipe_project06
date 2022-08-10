<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<div class="login_page">
	<div class="login_h">로그인</div>
	<div class="login_textline">
		<span class="login_idpw_text">아이디</span> <br> <input type="text"
			class="login_input_idpw" id="user_id" placeholder="아이디를 입력해주세요">
		<br> <span class="login_idpw_text">비밀번호</span> <input
			type="password" class="login_input_idpw" id="user_pass"
			placeholder="비밀번호를 입력해주세요"> <br> <input type="radio"
			name="session_check" value="session_check"> <span
			class="login_session_check">로그인 상태 유지</span> <span
			class="login_find_idpw"> <a href="#">아이디 찾기</a>/<a href="#">비밀번호
				찾기</a>
		</span>
	</div>
	<div class="login_btnline">
		<button type="button" class="login_loginbtn" id="login_loginbtn">로그인</button>
		<button type="button" class="login_signupbtn" onclick="location.href='/user/join'">회원가입</button>
	</div>
</div>

<script src="/resources/js/user.js"></script>
