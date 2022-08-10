<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<div class="signup_page">
	<div class="signup_h">${user.user_id}수정</div>
	<table class="signup_textline">
		<tr>
			<td><span class="signup_text">아이디</span></td>
			<td><input type="text" class="signup_input" id="user_id"
				value="${user.user_id}" readonly></td>
		</tr>
		<tr>
			<td><span class="signup_text">비밀번호</span></td>
			<td><input type="password" class="signup_input" id="user_pass"
				value="${user.user_pass}"></td>
		</tr>
		<tr>
			<td><span class="signup_text">비밀번호 확인</span></td>
			<td><input type="password" class="signup_input"
				id="user_pass_check" value="${user.user_pass}"></td>
		</tr>
		<tr>
			<td><span class="signup_text">이름</span></td>
			<td><input type="text" class="signup_input" id="user_name"
				value="${user.user_name}"></td>
		</tr>
		<tr>
			<td><span class="signup_text">이메일</span></td>
			<td><input type="email" class="signup_input" id="user_email"
				value="${user.user_email}"></td>
		</tr>
	</table>
	<div class="signup_btnline">
		<button type="button" class="signup_btn" id="modify_btn">수정하기</button>
	</div>
</div>
<script src="/resources/js/user.js"></script>
<%@ include file="../includes/footer.jsp"%>

