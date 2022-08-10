<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>

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
<div class="wrap">
	<div class="main">
	
	<div class="sidenav">
		<ul>
		<li><img id="index-image" src="/resources/images/main.jpg"></li>
		<li><img id="index-image" src="/resources/images/main2.jpg"></li>
		<li><img id="index-image" src="/resources/images/main.jpg"></li>
		<li><img id="index-image" src="/resources/images/main2.jpg"></li>
		</ul>
	</div>
	
	<div class="recipe_page">
	<div class="recipe_h">베스트 레시피</div>
	<ul class="recipe_menu">
		<c:forEach items="${list}" var="recipe">
			<li>
			<%-- <img class="recipe_image" src="/resources/upload/${recipe.save_folder }/${recipe.file_save}"> --%>
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
			
				<div class="recipe_insert">${recipe.ci_num}/${recipe.ci_writer}/
					${recipe.ci_regdate}</div> <br>
				<div class="recipe_title">
					<a
						href="/board/detail?ci_num=${recipe.ci_num}&pageNum=${p.currentPage}&field=${field}&word=${word}">${recipe.ci_title}/${recipe.ci_categories}</a>
				</div>
				<div class="recipe_content">${recipe.ci_content}</div>
				<div class="recipe_line"></div></li>
		</c:forEach>
	</ul>
</div>
<%@ include file="includes/footer.jsp"%>