<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<br>
<%@ include file="../includes/header.jsp"%>
<div class="recipe_page">
<section class="serv_list">
	<div class="container">
		<h2 style="text-align: center;">커뮤니티(${count})</h2>
		<div class="form-group text-right">
			<%-- 세션이 비어있지 않다면. 즉, 로그인을 하였을 때 --%>
			<c:if test="${not empty sessionScope.sMember}">
				<button type="button" class="btn btn-secondary btn-sm" id="btnWrite">글쓰기</button>
			</c:if>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일시</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="board">
					<tr>
						<td>${board.b_num}</td>
						<td><a
							href="/board/freedetail?b_num=${board.b_num}&pageNum=${p.currentPage}&field=${field}&word=${word}">${board.b_title}</a></td>
						<td>${board.b_writer}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd"
								value="${board.b_regdate}" /></td>
						<td>${board.b_count}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="d-flex justify-content-between mt-3">
			<ul class="pagination">
				<!-- 이전 -->
				<c:if test="${p.startPage>p.blockSize }">
					<li class="page-item"><a class="page-link"
						href="free?pageNum=${p.startPage-p.blockSize}&field=${field}&word=${word}">Previous</a></li>
				</c:if>
				<!--페이지 리스트-->
				<c:forEach begin="${p.startPage}" end="${p.endPage}" var="i">
					<li class="page-item"><a class="page-link"
						href="free?pageNum=${i}&field=${field}&word=${word}">${i}</a></li>
				</c:forEach>
				<!-- 다음 -->
				<c:if test="${p.endPage < p.totPage }">
					<li class="page-item"><a class="page-link"
						href="free?pageNum=${p.endPage+1}&field=${field}&word=${word}">Next</a></li>
				</c:if>
			</ul>

			<form class="form-inline" action="/board/free" id="searchFrm">
				<select name="field" class="form-control mb-2 mr-sm-2">
					<option value="" disabled selected>--</option>
					<option value="title" ${ field=='title' ? 'selected' : '' }>제목</option>
					<option value="writer" ${ field=='writer' ? 'selected' : '' }>내용</option>
					<option value="content" ${ (field=='content') ? 'selected' : '' }>작성자</option>
					<option value="cwt" ${ (field=='cwt') ? 'selected' : '' }>제목or내용or작성자</option>
				</select> <input type="text" class="form-control mb-2 mr-sm-2"
					placeholder="Enter Search" name="word" value="${word}">
				<button type="submit" class="btn btn-secondary mb-2 btn-sm">Search</button>
			</form>
		</div>
	</div>
</section>
<%@ include file="../includes/footer.jsp"%>
</div>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
	$("#btnWrite").click(function() {
		location.href = "/board/freeregister"
	});
</script>










