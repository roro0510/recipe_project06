<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<br>
<%@ include file="../includes/header.jsp"%>
<div class="recipe_page">
	<h2>${board.b_writer}의글보기</h2>

	<div class="form-group">
		<label for="num">글번호</label> <input type="text" class="form-control"
			id="b_num" name="b_num" value="${board.b_num}" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="title">제목</label> <input type="text" class="form-control"
			id="b_title" name="b_title" value="${board.b_title}"
			readonly="readonly">
	</div>
	<div class="form-group">
		<label for="writer">작성자</label> <input type="text"
			class="form-control" id="b_writer" name="b_writer"
			value="${board.b_writer}" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" rows="5" id="b_content"
			name="b_content" readonly="readonly">${board.b_content}</textarea>
	</div>
	<div class="form-group">
		<label for="writer">등록일</label> <input type="text"
			class="form-control" id="b_writer" name="b_writer"
			value="${board.b_regdate}" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="writer">조회수</label> <input type="text"
			class="form-control" id="" name="" value="${board.b_count}"
			readonly="readonly">
	</div>

	<div class="form-group text-right">
		<%-- 세션의 로그인 값과 게시글의 작성자가 같을 때. 즉, 로그인한 아이디와 게시글의 작성자가 같을 경우 --%>
		<c:if test="${sMember.user_id==board.b_writer || sMember.user_id=='admin'}">
			<button type="button" class="btn btn-secondary btn-sm" id="btnUpdate">수정하기</button>
			<button type="button" class="btn btn-secondary btn-sm" id="btnDelete">삭제하기</button>
		</c:if>
		<button type="button" class="btn btn-secondary btn-sm" id="btnList">목록보기</button>
	</div>


	<div class="container mt-5">
		<div class="form-group">
			<label for="comment">Comment:</label>
			<textarea class="form-control" rows="5" id="b_re_content" name="text"></textarea>
		</div>
		<button type="button" class="btn btn-success" id="replyBtn">Reply
			Write</button>
	</div>
	<div id="replyResult"></div>
	<%@ include file="../includes/footer.jsp"%>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
var init = function(){
	$.ajax({
		type:"get",
		url:"/replies/getList1/${board.b_num}.json",
		contentType:"application/json;charset=utf-8",
	})
	.done(function(resp){
		var str = "<table class='table table-hover mt-3'>";
		$.each(resp,function(key,val){
			str+="<tr>"
			str += "<td>" + val.b_re_writer + "</td>"
			str += "<td>" + val.b_re_content + "</td>"
			str += "<td>" + val.b_re_regdate + "</td>"
			
			if("${sessionScope.sMember.user_id}"==val.b_re_writer){
				str+= "<td><a href='javascript:fdel("+val.b_re_num+")'>삭제</a></td>"
			}
			
			str += "</tr>"
		})
		str += "</table>"
		$("#replyResult").html(str);
	})
};

	function fdel(b_re_num) {
		$.ajax({
			type : "delete",
			url : "/replies/" + b_re_num,
		}).done(function(resp) {
			alert(b_re_num + "번 글 삭제 완료");
			init();
		}).fail(function() {
			alert("댓글 삭제 실패")
		})
	}

	$("#replyBtn").click(function() {

		 	if(${empty sessionScope.sMember}){
		 		alert("로그인하세요");
		 		location.href="/user/login";
				return;
		 	} 
		var data = {
			"b_num" : $("#b_num").val(),
			"b_re_content" : $("#b_re_content").val(),
			"b_re_writer" : "${sMember.user_id}"
		}
		$.ajax({
			type : "post",
			url : "/replies/new1",
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify(data)
		}).done(function(resp) {
			alert("댓글 추가 성공");
			init();
		}).fail(function() {
			alert("댓글 추가 실패")
		});
	});

	$("#btnUpdate").click(function() {
		if (confirm("정말 수정할까요?")) {
			location.href = "/board/update1/${board.b_num}"
		}
	});

	$("#btnDelete").click(function() {
		if (confirm("정말 삭제할까요?")) {
			location.href = "/board/delete1/${board.b_num}"
		}
	});

	$("#btnList")
			.click(
					function() {
						if (confirm("정말 이동할까요?")) {
							location.href = "/board/free?pageNum=${pageNum}&field=${field}&word=${word}"
						}
					});

	init();
</script>
<%
String strReferer = request.getHeader("referer"); //이전 URL 가져오기

if (strReferer == null) {
%>
<script language="javascript">
	location.href = "/ban/detailban/${board.bno}";
</script>
<%
}
%>



