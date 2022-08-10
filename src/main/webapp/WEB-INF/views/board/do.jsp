<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>

<style>
	.sidenav2 {
    background-color: #fff;
    height: 100%;
    width:285px; 
    height: 280px;
     margin: 0 7px 19px 7px;
	}

	@media screen and (max-width: 767px) {
	    .sidenav2 {
	      height: auto;
	    }
	    .row.content {height:auto;} 
	  }
	  
	.sidenav2 ul, .sidenav li{list-style: none;}
	.sidenav2 ul, .sidenav li{list-style: none;}
	div.sidenav2 {width:285px; height: 280px; margin: 0 7px 19px 7px; overflow: hidden;}
	div.sidenav2 ul{width: calc(100% * 4); display: flex; animation: slide 8s infinite;}
	div.sidenav2 li{width: calc(100% / 4); height: 280px;}
	div.sidenav2 li:nth-child(1) {background-color: #fff;}
	div.sidenav2 li:nth-child(2) {background-color: #fff;}
	div.sidenav2 li:nth-child(3) {background-color: #fff;}
	div.sidenav2 li:nth-child(4) {background-color: #fff;}
	
	@keyframes slide {
	  0% {margin-left: 0;}
	  10% {margin-left: 0;}
	  25% {margin-left: -100%;}
	  35% {margin-left: -100%;}
	  50% {margin-left: -200%;}
	  60% {margin-left: -200%;}
	  75% {margin-left: -300%;}
	  85% {margin-left: -300%;}
	  100% {margin-left: 0;}
	}
</style>
<div class="recipe_page">
	<div class="recipe_h">디저트 TALK</div>
	<div class="category_tag">
        <a class="category_a" href="/board/ko">한식</a> 
        <a class="category_a" href="/board/jo">일식</a> 
        <a class="category_a" href="/board/co">중식</a> 
        <a class="category_a" href="/board/mo">양식</a> 
    	<a class="category_a" href="/board/do">디저트</a>
   	</div>
   	<div class="write_btn_position">
		<%-- 세션이 비어있지 않다면. 즉, 로그인을 하였을 때 --%>
		<c:if test="${not empty sessionScope.sMember}">
			<button type="button" class="write_btn" id="btnWrite">글쓰기</button>
		</c:if>
	</div>
	<ul class="recipe_menu">
		<c:forEach items="${list}" var="recipe">
			<li>
			<!-- <img class="recipe_image" src=""> -->
			<c:if test = "${(recipe.img1 ne '' && recipe.img2 eq '') && recipe.img3 eq ''}">
					<div class="sidenav2">
						<ul>
							<li>
								<c:if test = "${recipe.img1 ne ''}">
									<img class="recipe_image" src="/resources/upload/${recipe.img1_savefolder }/${recipe.img1}">	
								</c:if>
							</li>
							<li>
								<img class="recipe_image" src="/resources/upload/images.png">	
							</li>
							<li>
								<img class="recipe_image" src="/resources/upload/images.png">	
							</li>
							<li>
								<img class="recipe_image" src="/resources/upload/images.png">	
							</li>
						</ul>
					</div>
				</c:if>
				<c:if test = "${(recipe.img1 ne '' && recipe.img2 ne '') && recipe.img3 eq ''}">
					<div class="sidenav2">
						<ul>
							<li>
								<c:if test = "${recipe.img1 ne ''}">
									<img class="recipe_image" src="/resources/upload/${recipe.img1_savefolder }/${recipe.img1}">	
								</c:if>
							</li>
							<li>
								<c:if test = "${recipe.img2 ne ''}">
									<img class="recipe_image" src="/resources/upload/${recipe.img2_savefolder }/${recipe.img2}">	
								</c:if>
							</li>
							<li>
								<img class="recipe_image" src="/resources/upload/images.png">	
							</li>
							<li>
								<img class="recipe_image" src="/resources/upload/images.png">	
							</li>
						</ul>
					</div>
				</c:if>
				<c:if test = "${(recipe.img1 ne '' && recipe.img2 ne '') && recipe.img3 ne ''}">
					<div class="sidenav2">
						<ul>
							<li>
								<c:if test = "${recipe.img1 ne ''}">
									<img class="recipe_image" src="/resources/upload/${recipe.img1_savefolder }/${recipe.img1}">	
								</c:if>
							</li>
							<li>
								<c:if test = "${recipe.img2 ne ''}">
									<img class="recipe_image" src="/resources/upload/${recipe.img2_savefolder }/${recipe.img2}">	
								</c:if>
							</li>
							<li>
								<c:if test = "${recipe.img3 ne ''}">
									<img class="recipe_image" src="/resources/upload/${recipe.img3_savefolder }/${recipe.img3}">
								</c:if>
							</li>
							<li>
								<img class="recipe_image" src="/resources/upload/images.png">	
							</li>
						</ul>
					</div>
				</c:if>
				
				<c:if test= "${(recipe.img1 eq '' && recipe.img2 eq '') && recipe.img3 eq ''}">
					 <img class="recipe_image" src="/resources/upload/images.png">
				</c:if>
			
			<div class="recipe_insert">${recipe.ci_num}/${recipe.ci_writer}/${recipe.ci_regdate}</div> <br>
			<div class="recipe_title">
			<a href="/board/detail?ci_num=${recipe.ci_num}&pageNum=${p.currentPage}&field=${field}&word=${word}">${recipe.ci_title}/${recipe.ci_categories}</a>
			</div>
			<div class="recipe_content">${recipe.ci_content}</div>
			<div class="recipe_line"></div>
			</li>
		</c:forEach>
	</ul>
	<div class="d-flex justify-content-between mt-3">
			<ul class="recipe_paging">
				<!-- 이전 -->
				<c:if test="${p.startPage>p.blockSize }">
					<li class="page-item"><a class="page-link"
						href="list?pageNum=${p.startPage-p.blockSize}&field=${field}&word=${word}">Previous</a></li>
				</c:if>
				<!--페이지 리스트-->
				<c:forEach begin="${p.startPage}" end="${p.endPage}" var="i">
					<li class="recipe_pagination"><a class="recipe_pagination"
						href="list?pageNum=${i}&field=${field}&word=${word}">${i}</a></li>
				</c:forEach>
				<!-- 다음 -->
				<c:if test="${p.endPage < p.totPage }">
					<li class="page-item"><a class="page-link"
						href="list?pageNum=${p.endPage+1}&field=${field}&word=${word}">Next</a></li>
				</c:if>
			</ul>
		</div>
		<%@ include file="../includes/footer.jsp"%>
	</div>
	

<script type="text/javascript">
	$("#btnWrite").click(function() {
		location.href = "/board/register"
	});
	

</script>