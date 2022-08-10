package com.recipe.project06.controller;

import java.util.HashMap;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recipe.project06.HomeController;
import com.recipe.project06.entity.PageVO;
import com.recipe.project06.entity.User_Master;
import com.recipe.project06.service.UserService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/user/*")
public class UserController {
	@Autowired
	private UserService userservice;

	@GetMapping("admin")
	public void admin() {

	}

	@GetMapping("login")
	public void login(HttpServletRequest req) {
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 Login에 접속.");
	}

	@PostMapping("login")
	@ResponseBody
	public String login(String user_id, String user_pass, HttpSession session, HttpServletRequest req) {

		User_Master user = userservice.loginCheck(user_id);
		String result = "no"; // 회원아닐때
		// member가 null이 아니면. 입력된 id가 있고 sql구문을 통해서 검색된 member의 값이 있을 경우.
		if (user != null) {
			// 입력된 비밀번호가 저장된 비밀번호와 일치할 경우.
			if (user.getUser_pass().equals(user_pass)) {
				// 로그인 정보 출력
				log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 아이디:" + user.getUser_id() + ",비밀번호:" + user_pass
						+ "의 계정으로 로그인.");
				// 새로운 세션을 생성한다. 세션은 로그인이 유지되는 것을 말한다.
				session.setAttribute("sMember", user);
				result = "success";
			} else {
				result = "passFail";
			}
		}
		return result;
	}

	@GetMapping("denied")
	public String denied() {
		return "user/denied";
	}

//	@RequestMapping("address.do")
//    public String address() {
//        return "user/join";
//    }
	
	
	@PostMapping("join")
	@ResponseBody
	public String join(@RequestBody User_Master user, HttpServletRequest request) {

		// member의 getId() 메소드로 아이디 값을 받아서 memberService의 idCheck() 메소드로 해당 아이디를 검색한다.
		// 입력된 아이디가 중복된 값인지를 확인
		int cnt = userservice.idCheck(user.getUser_id());
		
		// cnt의 값이 ture이면 1의 값을 가지므로 아래의 if문에 해당함.
		if (cnt != 0) {
			return "fail";
		}
		userservice.join(user);
		
		log.info(HomeController.etRemoteAddr(request) + "의 아이피에서 " + user.getUser_id() + "계정으로 회원가입.");
		return "success";
	}

	@GetMapping("join")
	public void join() {

	}

	@GetMapping("modify/{user_id}")
	public String modify(@PathVariable("user_id") String id, Model model, HttpServletRequest req) {
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + id + "계정을 수정하는 중.");
		model.addAttribute("user", userservice.read(id));
		return "user/modify";
	}

	@PostMapping("modify")
	@ResponseBody
	public String modify(@RequestBody User_Master usermaster, HttpServletRequest request) {
		userservice.modify(usermaster);
		log.info(HomeController.etRemoteAddr(request) + "의 아이피에서 " + usermaster.getUser_id() + " 계정을 수정.");
		return "success";
	}

	@GetMapping("userlist")
	public String userlist(Model model, String pageNum, HttpServletRequest req,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word) {

		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 userList에 접속.");
		// 페이지 수가 null인 경우를 예외처리
		if (!(pageNum == null)) {
			log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 userList " + pageNum + "번의 페이지를 읽음.");
		}
		int pageSize = 5; // 한 블럭에 보일 맴버 수

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		hm.put("pageStart", (currentPage - 1) * pageSize);
		hm.put("pageSize", pageSize);

		List<User_Master> list = userservice.findAll(hm);
		int usercount = userservice.userCount(hm);
		PageVO page = new PageVO(usercount, currentPage, pageSize);

		model.addAttribute("userlist", list);
		model.addAttribute("count", usercount);
		model.addAttribute("p", page);
		model.addAttribute("field", field);
		model.addAttribute("word", word);

		return "user/userlist";

	}

	// 회원추방
	@GetMapping("ban/{user_id}")
	public String ban(@PathVariable("user_id") String id, Model model, HttpSession session, HttpServletRequest req) {
		String strReferer = req.getHeader("referer"); // 이전 URL 가져오기
		// 직접 접근 막기
		if (strReferer == null) {
			log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + id + "계정 추방을 잘못된 경로로 접속.");
			return "redirect:/error";
		}
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + id + " 계정이 추방.");
		userservice.delete(id);
		return "redirect:/user/userlist";
	}

	// 로그아웃 메소드
	@GetMapping("logout")
	public String logout(HttpSession session, HttpServletRequest req) {
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 로그아웃 됨.");
		session.invalidate();
		// 제자리 걸음.
		return "redirect:/index";
	}

}
