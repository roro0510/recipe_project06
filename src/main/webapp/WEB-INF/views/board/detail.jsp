<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	
<%@ include file="../includes/header.jsp"%>
<div class="recipe_page">
	<div id="section">
	<br>
	<table class="table">
				<thead>
					<tr>
						<th width="30%" style="font-size: 30px; font-weight: 500;">${recipe.ci_writer}의 글보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="5%" style="font-size: 20px; font-weight: 500;">레시피 제목</th>
						<td><input type="text" id="recipe_title" class="form-control"
							name="ci_title" value="${recipe.ci_title}"
							readonly="readonly"></td>
						<td rowspan="2" style="width:200px; height:100px;">
							<div style="text-align: center;">요리 대표 사진</div>
							<hr>
							<div>
								<ul>
									<c:forEach items="${recipe.fileList}" var="fileInfo">
										<li style="list-style: nome">
										<c:choose>
												<%-- 이미지 파일인 경우 --%>
												<c:when test="${fileInfo.file_type=='image'}">
													<img
														src="/resources/upload/${fileInfo.save_folder}/${fileInfo.file_save}"
														height="50">
												</c:when>
					
												<c:otherwise>
													<img src="/resources/upload/file.png" height="50">
												</c:otherwise>
											</c:choose> ${fileInfo.file_origin} <a class="filedown" href="#"
											fno="${fileInfo.file_num}" sfolder="${fileInfo.save_folder}"
											ofile="${fileInfo.file_origin }" sfile="${fileInfo.file_save}">[다운로드]</a>
										</li>
									</c:forEach>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<th width="10%" style="font-size: 20px; font-weight: 500;">작성자</th>
						<td><input type="text" class="form-control" id="ci_writer"
							name="ci_writer" value="${recipe.ci_writer}" readonly></td>
					</tr>
					<tr>
						<th width="10%" style="font-size: 20px; font-weight: 500;">요리소개</th>
						<td><textarea id="recipe_intro" class="form-control" rows="4"
							name="ci_content" readonly="readonly">${recipe.ci_content}</textarea></td>
						<td><div id="image_container" ></div></td>								
					</tr>
					<tr>
						<th width="10%" style="font-size: 20px; font-weight: 500;"> 카테고리</th>
						<td colspan="2"> <input style="width: 100px; text-align: center;" type="text"
						class="form-control" id="ci_categories" name="ci_categories"
						value="${recipe.ci_categories}" readonly="readonly"></td>
					</tr>
					<tr>
						<th width="10%" style="font-size: 20px; font-weight: 500;">요리설명</th>
						<td colspan="3"><textarea class="form-control" name="ci_explanation" rows="10"
						readonly="readonly">${recipe.ci_explanation}</textarea></td>
					</tr>
					<tr>
						<th width="10%" style="font-size: 20px; font-weight: 500;">조회수</th>
						<td colspan="3"><input style="width: 100px; text-align: center;" type="text"
						class="form-control" id="" name="" value="${recipe.ci_count}"
						readonly="readonly"></td>
					</tr>
				</tbody>
			</table>
			<div class="form-group text-right">
			<%-- 세션의 로그인 값과 게시글의 작성자가 같을 때. 즉, 로그인한 아이디와 게시글의 작성자가 같을 경우 --%>
			<c:if
				test="${sMember.user_id==recipe.ci_writer || sMember.user_id=='admin'}">
				<button type="button" class="btn btn-secondary btn-sm" id="btnUpdate">수정하기</button>
				<button type="button" class="btn btn-secondary btn-sm" id="btnDelete">삭제하기</button>
			</c:if>
			<button type="button" class="btn btn-secondary btn-sm" id="btnList">목록보기</button>
			</div>	
	</div>
			
			<div class="container mt-5">
				<div class="form-group">
					<label for="comment">Comment:</label>
					<textarea class="form-control" rows="5" id="ci_re_content"
						name="text"></textarea>
				</div>
				<button type="button" class="btn btn-success" id="replyBtn">Reply
					Write</button>
			</div>
			<div id="replyResult"></div>		
</div>





<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">

$(".filedown").click(function(){
	var fno=$(this).attr('fno');
	alert("fno:"+fno+" 원본:"+$(this).attr('ofile')+" 실제: "+$(this).attr('sfile'));
	location.href="/board/download/"+fno;
});

var init = function(){
	$.ajax({
		type:"get",
		url:"/ci_replies/getList/${recipe.ci_num}.json",
		contentType:"application/json;charset=utf-8",
	})
	.done(function(resp){
		var str = "<table class='table table-hover mt-3'>";
		$.each(resp,function(key,val){
			str+="<tr>"
			str += "<td>" + val.ci_re_writer + "</td>"
			str += "<td>" + val.ci_re_content + "</td>"
			str += "<td>" + val.ci_re_regdate + "</td>"
			
			if("${sessionScope.sMember.user_id}"==val.ci_re_writer){
				str+= "<td><a href='javascript:fdel("+val.ci_re_num+")'>삭제</a></td>"
			}
			
			str += "</tr>"
		})
		str += "</table>"
		$("#replyResult").html(str);
	})
};

function fdel(ci_re_num){
	$.ajax({
		type:"delete",
		url:"/ci_replies/"+ci_re_num,
	})
	.done(function(resp){
		alert(ci_re_num+"번 글 삭제 완료");
		init();
	})
	.fail(function(){
		alert("댓글 삭제 실패")
	})
}

$("#replyBtn").click(function(){
	
	if(${empty sessionScope.sMember}){
		alert("로그인하세요");
		location.href="/user/login";
		return;
	}
	var data={
			"ci_num":$("#ci_num").val(),
			"ci_re_content":$("#ci_re_content").val(),
			"ci_re_writer":"${sMember.user_id}"
	}
	$.ajax({
		type:"post",
		url:"/ci_replies/new",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data)
	})
	.done(function(resp){
		alert("댓글 추가 성공");
		init();
	})
	.fail(function(){
		alert("댓글 추가 실패")
	});
});

$("#btnUpdate").click(function(){
	if(confirm("정말 수정할까요?")){
		location.href="/board/update/${recipe.ci_num}"
	}
});

$("#btnDelete").click(function(){
	if(confirm("정말 삭제할까요?")){
		location.href="/board/delete/${recipe.ci_num}"
	}
});

$("#btnList").click(function(){
	if(confirm("정말 이동할까요?")){
		location.href="/board/list?pageNum=${pageNum}&field=${field}&word=${word}"
	}
});

init();

</script>



