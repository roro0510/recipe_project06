/**
 * 
 */

$(document).ready(function() {
	// 회원가입 버튼을 누를 때 
	$("#signup_btn").click(function() {
		// 유효성 검사
		if ($("#user_id").val() == "") {
			alert("아이디를 입력하세요");
			$("#user_id").focus();
			return false;
		}
		if ($("#user_pass").val() == "") {
			alert("비밀번호를 입력하세요");
			$("#user_pass").focus();
			return false;
		}
		if ($("#user_pass_check").val() == "") {
			alert("비밀번호 확인을 입력하세요");
			$("#user_pass_check").focus();
			return false;
		}
		if ($("#user_pass").val() != $("#user_pass_check").val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#user_pass_check").focus();
			return false;
		}
		if ($("#user_name").val() == "") {
			alert("이름을 입력하세요");
			$("#user_name").focus();
			return false;
		}
		if ($("#user_email").val() == "") {
			alert("이메일을 입력하세요");
			$("#user_email").focus();
			return false;
		}

		var data = {
			// 해당 아이디의 입력된 값들을 변수에 저장
			"user_id":$("#user_id").val(),
			"user_pass":$("#user_pass").val(),
			"user_name":$("#user_name").val(),
			"user_email":$("#user_email").val(),
			"addr1":$("#addr1").val(),
			"addr2":$("#addr2").val(),
			"addr3":$("#addr3").val()
		}

		$.ajax({
			type:"post", //전송 방식
			url:"/user/join", //
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data) //json데이터로 만들어서 전송
		})
			// ajax의 모든 과정이 끝나고 res의 값을 가져와서 해당 function을 실행한다.
			// res의 값은 Memcontroller의 join메소드에서 return되는 값을 의미한다.
			.done(function(res) {
				if(res == "success") {
					alert("회원가입을 축하합니다");
					location.href = "/user/login";
				}else if (res == "fail") {
					alert("아이디 중복확인하세요");
					$("#user_id").val("")
				}
			})

	})

	$("#modify_btn").click(function() {
		// 유효성 검사
		if ($("#user_id").val() == "") {
			alert("아이디를 입력하세요");
			$("#user_id").focus();
			return false;
		}
		if ($("#user_pass").val() == "") {
			alert("비밀번호를 입력하세요");
			$("#user_pass").focus();
			return false;
		}
		if ($("#user_pass_check").val() == "") {
			alert("비밀번호 확인을 입력하세요");
			$("#user_pass_check").focus();
			return false;
		}
		if ($("#user_pass").val() != $("#user_pass_check").val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#user_pass_check").focus();
			return false;
		}
		if ($("#user_email").val() == "") {
			alert("이메일을 입력하세요");
			$("#user_email").focus();
			return false;
		}
		if ($("#user_name").val() == "") {
			alert("이름을 입력하세요");
			$("#user_name").focus();
			return false;
		}

		var data = {
			// 해당 아이디의 입력된 값들을 변수에 저장
			"user_id": $("#user_id").val(),
			"user_pass": $("#user_pass").val(),
			"user_email": $("#user_email").val(),
			"user_name": $("#user_name").val(),
			"addr1":$("#addr1").val(),
			"addr2":$("#addr2").val(),
			"addr3":$("#addr3").val()
		}

		$.ajax({
			type: "post", //전송 방식
			url: "/user/modify", //전송 링크
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify(data) //json데이터로 만들어서 전송
		})
			// ajax의 모든 과정이 끝나고 res의 값을 가져와서 해당 function을 실행한다.
			// res의 값은 Memcontroller의 join메소드에서 return되는 값을 의미한다.
			.done(function(res) {
				if (res == "success") {
					alert("회원수정이 완료되었습니다");
					location.href = "/index";
				}
			})

	})
	
	$("#login_loginbtn").click(function() {
		$.ajax({
			type: "post",
			url: "/user/login",
			data: {
				"user_id": $("#user_id").val(),
				"user_pass": $("#user_pass").val(),
			}
		})
			// resp의 값은 Memcontroller의 login메소드에서 return되는 값을 의미한다.
			.done(function(resp) {
				if (resp == "no") {
					alert("회원이 아닙니다. 회원가입을 하세요");
					location.href = "join";
				} else if (resp == "success") {
					alert("로그인 성공");
					location.href = "/board/list";
				} else {
					alert("비밀번호를 확인하세요");
				}
			})
	})

});

function del(userid) {
	if (confirm("정말 삭제할까요?")) {
		$.getJSON("userDelete",
			{ "userid": userid },
			function(resp) {
				var str = "";
				$.each(resp.jarr, function(key, val) {
					str += "<tr>";
					str += "<td>" + val.name + "</td>"
					str += "<td>" + val.userid + "</td>"
					str += "<td>" + val.phone + "</td>"
					str += "<td>" + val.email + "</td>"
					if (val.admin == 0) {
						str += "<td>일반회원</td>"
						str += "<td onclick=del('" + val.userid + "')>삭제</td>"
					} else {
						str += "<td>관리자</td>"
						str += "<td>admin</td>"
					}
					str += "</tr>"
				})
				$("table tbody").html(str);
				$("#cntSpan").text(resp.count);
			});

	}
}