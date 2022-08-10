package com.recipe.project06.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.java.Log;

//16번 작업 -> Security-context.xml
@Log
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub

		log.warning("login Test...");
		// 권한을 넣을 리스트를 만듭니다
		List<String> authList = new ArrayList<>();
     
     // 디버깅 테스트
		log.warning("getCredential : " + authentication.getCredentials());
		log.warning("principal : " + authentication.getPrincipal());
		log.warning("들어오는 사용자 : " + authentication.getName());
		log.warning("가진 권한  : " + authentication.getAuthorities());
     
		// 들어온 사용자의 권한을 authList에 추가 (여러개일 수 있으므로 하나씩 담음.)
		authentication.getAuthorities().forEach(auth -> {
			authList.add(auth.getAuthority());
		});

		// 관리자 일 경우
		if (authList.contains("ROLE_ADMIN")) {
			log.warning("관리자 입니다.");
			response.sendRedirect("/user/admin");
			return;
		}
		// 회원가입한 사용자일 경우.
		if (authList.contains("ROLE_MEMBER")) {
			log.warning("회원가입된 사용자 입니다.");
			response.sendRedirect("/user/member");
			return;
		}
		// 실패했을 경우 그대로 loginPage.jsp로 돌아갑니다.
		response.sendRedirect("/");
	}
}