<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<script src="/resources/js/summernote/summernote-lite.js"></script>
<script src="/resources/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet"
	href="/resources/css/summernote/summernote-lite.css">

<%@ include file="../includes/header.jsp"%>
<div class="recipe_page">
<div id="section">
<form action="/board/update" method="post">
<table class="table">
<thead>
<tr><th width="15%" style="font-size: 30px; font-weight: 500;">레시피 수정</th></tr>
</thead>
<tbody>
<tr>
	<th width="15%" style="font-size: 20px; font-weight: 500;">레시피 번호</th>
	<td><input type="text" class="form-control" id="ci_num" name="ci_num" value="${recipe.ci_num}" readonly></td>
</tr>
<tr>
	<th width="15%" style="font-size: 20px; font-weight: 500;">레시피 제목</th>
	<td><input type="text" id="ci_title" class="form-control" name="ci_title" placeholder="${recipe.ci_title}"></td>
	<!-- <td rowspan="2" style="width:200px; height:100px;">
	<div style="text-align: center;">요리 대표 사진을 등록 해주세요</div>
	<hr> 
	<input type="file" id="uploads" accept="image/*" onchange="setThumbnail(event);" name="uploads" multiple />
	</td> -->
</tr>
<tr>
	<th width="15%" style="font-size: 20px; font-weight: 500;">작성자</th>
	<td><input type="text" class="form-control" id="ci_writer" name="ci_writer" value="${sMember.user_id}" readonly></td>
</tr>
<tr>
	<th width="15%" style="font-size: 20px; font-weight: 500;">요리소개</th>
	<td><textarea id="ci_content" class="form-control" rows="4" name="ci_content">${recipe.ci_content}</textarea></td>
</tr>
<tr>
	<th width="15%" style="font-size: 20px; font-weight: 500;">카테고리</th>
	<td colspan="2">
		<select class="form-select" id="ci_categories" name="ci_categories" style="width: 100px; height: 40px;">
		<option selected value="--">종류별</option>
		<option value="한식">한식</option>
		<option value="중식">중식</option>
		<option value="일식">일식</option>
		<option value="양식">양식</option>
		<option value="디저트">디저트</option>
		</select>
	</td>
</tr>
<tr>
	<th width="15%" style="font-size: 20px; font-weight: 500;">요리설명</th>
	<td colspan="3"><textarea id="ci_explanation" name="ci_explanation" class="summernote">${recipe.ci_explanation}</textarea></td>
</tr>
</tbody>
</table>
	<span style='float: right'>
	<!-- <button type="submit" class="btn btn-secondary mb-3">취소</button> -->
	<button type="submit" class="btn btn-secondary mb-3">등록</button>
	</span>
</form>
</div>
<%@ include file="../includes/footer.jsp"%>
</div>



<script>
	$('.summernote').summernote({
		height : 450,
		lang : "ko-KR",
		placeholder : '이 레시피의 재료와 순서를 설명해 주세요.'
	});
	function setThumbnail(event) {
		var reader = new FileReader();
		reader.onload = function(event) {
			var img = document.createElement("img");
			img.setAttribute("src", event.target.result);
			document.querySelector("div#image_container").appendChild(img);
		};

		reader.readAsDataURL(event.target.files[0]);
	}
</script>