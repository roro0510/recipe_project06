<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<%@ include file="../includes/header.jsp"%>
<div class="recipe_page">
	<h2>글쓰기</h2>
	<form action="insert1" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">제목:</label> <input type="text"
				class="form-control" id="b_title" placeholder="Enter title"
				name="b_title">
		</div>
		<div class="form-group">
			<label for="writer">작성자:</label> <input type="text"
				class="form-control" id="b_writer" name="b_writer"
				value="${sMember.user_id}" readonly>
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" rows="5" id="b_content"
				name="b_content"></textarea>
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
	location.href = "/ban/insertban";
</script>
<%
}
%>
