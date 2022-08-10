<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<br>
<section class="serv_list">
	<div class="container">
		<h3>회원 목록(${count})</h3>
		<br>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>아이디</th>
					<th>비밀번호</th>
					<th>이메일</th>
					<th>이름</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userlist}" var="userlist">
					<tr>
						<%-- MemberList에서 회원 수정이 가능. --%>
						<td><a href="/user/modify/${userlist.user_id}">${userlist.user_id}</a></td>
						<td>${userlist.user_pass}</td>
						<td>${userlist.user_email}</td>
						<td>${userlist.user_name}</td>
						<%-- 회원추방 --%>
						<td><a href="/user/ban/${userlist.user_id}" id="btnDelete">회원추방</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="d-flex justify-content-between mt-3">
			<ul class="pagination">
				<!-- 이전 -->
				<c:if test="${p.startPage>p.blockSize }">
					<li class="page-item"><a class="page-link"
						href="userlist?pageNum=${p.startPage-p.blockSize}&field=${field}&word=${word}">Previous</a></li>
				</c:if>
				<!--페이지 리스트-->
				<c:forEach begin="${p.startPage}" end="${p.endPage}" var="i">
					<li class="page-item"><a class="page-link"
						href="userlist?pageNum=${i}&field=${field}&word=${word}">${i}</a></li>
				</c:forEach>
				<!-- 다음 -->
				<c:if test="${p.endPage < p.totPage }">
					<li class="page-item"><a class="page-link"
						href="userlist?pageNum=${p.endPage+1}&field=${field}&word=${word}">Next</a></li>
				</c:if>
			</ul>
			<form class="form-inline" action="/user/userlist" id="searchFrm">
				<select name="field" class="form-control mb-2 mr-sm-2">
					<option value="" disabled selected>--</option>
					<option value="user_id" ${ field=='user_id' ? 'selected' : '' }>아이디</option>
					<option value="user_pass" ${ field=='user_pass' ? 'selected' : '' }>이름</option>
					<option value="user_email"
						${ (field=='user_email') ? 'selected' : '' }>이메일</option>
					<option value="user_tel"
						${ (field=='user_name') ? 'selected' : '' }>가입날짜</option>
				</select> <input type="text" class="form-control mb-2 mr-sm-2"
					placeholder="Enter Search" name="word" value="${word}">
				<button type="submit" class="btn btn-secondary mb-2 btn-sm">Search</button>
			</form>
		</div>
	</div>
</section>
</body>
</html>