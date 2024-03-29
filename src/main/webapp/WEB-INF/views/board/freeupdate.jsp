<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<%@ include file="../includes/header.jsp"%>
<div class="recipe_page">
	<h2>글쓰기</h2>
	<form action="/board/update1" method="post">
		<div class="form-group">
			<label for="bno">번호:</label> <input type="text" class="form-control"
				id="b_num" name="b_num" value="${board.b_num}" readonly>
		</div>
		<div class="form-group">
			<label for="title">제목:</label> <input type="text"
				class="form-control" id="b_title" name="b_title" value="${board.b_title}">
		</div>
		<div class="form-group">
			<label for="writer">작성자:</label> <input type="text"
				class="form-control" id="b_writer" name="b_writer"
				value="${board.b_writer}" readonly>
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" rows="5" id="b_content" name="b_content">${board.b_content}</textarea>
		</div>
		<button type="submit" class="btn btn-primary btn-sm">Submit</button>
	</form>
<%@ include file="../includes/footer.jsp"%>
</div>
<%
String strReferer = request.getHeader("referer"); //이전 URL 가져오기

if (strReferer == null) {
%>
<script language="javascript">
	location.href = "/ban/updateban/${board.bno}";
</script>
<%
}
%>
