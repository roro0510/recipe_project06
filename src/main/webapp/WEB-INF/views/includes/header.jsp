<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>Recipe Talk</title>
<link rel="stylesheet" href="/resources/css/sidebar.css" />
<link rel="stylesheet" href="/resources/css/reset.css" type="text/css">
<link rel="stylesheet" href="/resources/css/main.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/sidebar.js"></script>
    <script type="text/javascript" src="/resources/js/jQueryRotateCompressed.js"></script>
</head>
<!-- 사이드 메뉴 -->
<body>
	<div class="wrap">
		<div class="header">
			<div id="userMenu">
				<ul class="list_menu">
					<c:choose>
						<c:when test="${empty sessionScope.sMember}">
							<li class="login"><a href="/user/login">로그인</a></li>
							<li class="signup"><a href="/user/join">회원가입</a></li>
						</c:when>
						<c:otherwise>
							<%-- 로그인 정보표시 및 로그아웃 버튼 표시 --%>
							<li class="userlist"><a class="modify"
								href="/user/modify/${sMember.user_id}">My
									info(${sMember.user_id})</a></li>

							<li class="logout"><a class="logout" href="/user/logout">Logout(${sMember.user_id})</a></li>
						</c:otherwise>
					</c:choose>
					<c:if test="${sMember.user_id  == 'admin' }">
						<li class="userlist"><a href="/user/userlist">회원목록</a></li>
					</c:if>
				</ul>
			</div>
			<div style="padding-top: 50px" id="logo"><a style="text-decoration: none" href ="/index" >레시피TALK</a></div>
			<div class="nav_menu">
				<ul>
					<li class="menu1"><a href="/board/rankpage">베스트</a></li>
					<li class="menu2"><a href="/board/free">게시판</a></li>
					<li class="menu3"><a href="/board/list">레시피</a></li>
					<li class="menu3"><a href="/board/event">오늘 뭐 먹지?</a></li>
					<li class="menu3"><a href="/board/map">주변 마트 찾기!</a></li>
				</ul>
				<div class="nav_search">
					<form class="search_bar" action="/board/search" id="searchFrm">
						<input class="search_bar" type="text" name="word" value="${word}"
							placeholder="레시피 통합 검색">
						<button type="submit" class="btn btn-secondary mb-2 btn-sm"><img class="search_btn" src="/resources/images/Search.png" style="border: none;"></button>
					</form>
				</div>
			</div>
			<div class="Line-1"></div>
		</div>
	</div>