package com.recipe.project06.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.recipe.project06.entity.Img;
import com.recipe.project06.entity.PageVO;
import com.recipe.project06.entity.Recipe;
import com.recipe.project06.entity.Recipe_File;
import com.recipe.project06.service.RecipeBoardService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/board/")
public class RecipeController {

	@Value("${spring.servlet.multipart.location}")
	String filePath;

	@Autowired
	RecipeBoardService recipeBoardService;

	@GetMapping("register")
	public void register() {

	}
	
	@GetMapping("event")
	public void lucky() {
		
	}
	
	@GetMapping("map")
	public void infomap() {
		
	}
	
	@PostMapping("insert")
	public String register(Recipe recipe, MultipartFile[] uploads, HttpSession session) {
		String uploadFolder = session.getServletContext().getRealPath("/resources/upload");

		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadFolder + File.separator + today;
		System.out.println(saveFolder);
		File folder = new File(saveFolder);

		if (!folder.exists()) {
			folder.mkdirs();
		}
		List<Recipe_File> fileList = new ArrayList<Recipe_File>();
		int i = 1;
		for (MultipartFile multipartFile : uploads) {
			String originFile = multipartFile.getOriginalFilename();

			if (!originFile.isEmpty()) {
				Recipe_File recipe_File = new Recipe_File();
				UUID uuid = UUID.randomUUID();
				String saveFileName = uuid.toString() + "_" + originFile;
				String fileType = multipartFile.getContentType();
				log.info("fileType:" + fileType);
				fileType = fileType.substring(0, fileType.indexOf("/"));
				recipe_File.setFile_num(i);
				recipe_File.setFile_type(fileType);
				recipe_File.setFile_origin(originFile);
				recipe_File.setFile_save(saveFileName);
				recipe_File.setSave_folder(today);
				
				
				try {
					File savefile = new File(saveFolder, saveFileName);
					multipartFile.transferTo(savefile);
					fileList.add(recipe_File);

				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			i ++;
		}
		recipe.setFileList(fileList);
		recipeBoardService.insert(recipe);

		return "redirect:/board/list";

	}

	@GetMapping("update/{ci_num}")
	public String update(@PathVariable("ci_num") int ci_num, Model model) {
		model.addAttribute("recipe", recipeBoardService.read(ci_num));
		return "board/update";
	}

	@PostMapping("update")
	public String update(Recipe vo) {
		recipeBoardService.update(vo);
		return "redirect:/board/list";
	}

	@GetMapping("delete/{ci_num}")
	public String delete(@PathVariable("ci_num") int ci_num) {
		recipeBoardService.delete(ci_num);
		return "redirect:/board/list";
	}

	@GetMapping("list")
	public String list(Model model, String pageNum, @RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word) {
		log.info("listController");

		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		System.out.println(currentPage);

		int pageSize = 8;

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		hm.put("pageStart", (currentPage - 1) * pageSize);
		hm.put("pageSize", pageSize);

		List<Recipe> list = recipeBoardService.findAll(hm);
		int count = recipeBoardService.boardCount(hm);
		PageVO page = new PageVO(count, currentPage, pageSize);

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		model.addAttribute("field", field);
		model.addAttribute("word", word);
		return "board/list";
	}

	@GetMapping("search")
	public String search(Model model, String pageNum, @RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word) {
		log.info("headerController");

		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		System.out.println(currentPage);

		int pageSize = 8;

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		hm.put("pageStart", (currentPage - 1) * pageSize);
		hm.put("pageSize", pageSize);

		List<Recipe> list = recipeBoardService.totalfindAll(hm);
		int count = recipeBoardService.totalboardCount(hm);
		PageVO page = new PageVO(count, currentPage, pageSize);

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		model.addAttribute("field", field);
		model.addAttribute("word", word);
		return "board/list";
	}

	@GetMapping("download/{file_num}")
	public String download(@PathVariable("file_num") int file_num, HttpSession session, HttpServletResponse res,
			HttpServletRequest req) {

		Recipe_File fileVO = recipeBoardService.getFile(file_num);
		log.info("fileVO............." + fileVO);
		String fileName = null;

		try {
			String path = session.getServletContext().getRealPath("/resources/upload/" + fileVO.getSave_folder());
			File file = new File(path, fileVO.getFile_save());

			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

			String header = req.getHeader("User-Agent");

			if ((header.contains("MSIE")) || (header.contains("Trident")) || (header.contains("Edge"))) {
				// 인터넷 익스플로러 10이하 버전, 11버전, 엣지에서 인코딩
				fileName = URLEncoder.encode(fileVO.getFile_origin(), "UTF-8");
			} else {
				// 나머지 브라우저에서 인코딩
				fileName = new String(fileVO.getFile_origin().getBytes("UTF-8"), "iso-8859-1");
			}
			log.info(fileName);
			res.setContentType("application/octet-stream");
			// 다운로드와 다운로드될 파일이름
			res.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
			// 파일 복사
			FileCopyUtils.copy(in, res.getOutputStream());
			in.close();
			res.getOutputStream().flush();
			res.getOutputStream().close();

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

		return "aaa";
	}

	@GetMapping("detail")
	public String read(@RequestParam("ci_num") int ci_num, String pageNum,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word, Model model) {
		model.addAttribute("recipe", recipeBoardService.read(ci_num));
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("field", field);
		model.addAttribute("word", word);
		return "board/detail";
	}

	@GetMapping("ko")
	public String listko(Model model, String pageNum, HttpServletRequest req,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word, Recipe vo) {

		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		int pageSize = 5; // 한 블럭에 보일 게시글 수

		// 게시글 검색
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		hm.put("pageStart", (currentPage - 1) * pageSize);
		hm.put("pageSize", pageSize);

		List<Recipe> list = recipeBoardService.getListko(vo);
		int count = recipeBoardService.boardCount(hm);
		PageVO page = new PageVO(count, currentPage, pageSize);

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		model.addAttribute("field", field);
		model.addAttribute("word", word);
		return "board/ko";
	}


	@GetMapping("jo")
	public String list1(Model model, String pageNum, HttpServletRequest req,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word, Recipe vo) {

		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		int pageSize = 5; // 한 블럭에 보일 게시글 수

		// 게시글 검색
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		hm.put("pageStart", (currentPage - 1) * pageSize);
		hm.put("pageSize", pageSize);

		List<Recipe> list = recipeBoardService.getListjo(vo);
		int count = recipeBoardService.boardCount(hm);
		PageVO page = new PageVO(count, currentPage, pageSize);

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		model.addAttribute("field", field);
		model.addAttribute("word", word);
		return "board/jo";
	}

	@GetMapping("co")
	public String list2(Model model, String pageNum, HttpServletRequest req,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word, Recipe vo) {

		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		int pageSize = 5; // 한 블럭에 보일 게시글 수

		// 게시글 검색
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		hm.put("pageStart", (currentPage - 1) * pageSize);
		hm.put("pageSize", pageSize);

		List<Recipe> list = recipeBoardService.getListco(vo);
		int count = recipeBoardService.boardCount(hm);
		PageVO page = new PageVO(count, currentPage, pageSize);

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		model.addAttribute("field", field);
		model.addAttribute("word", word);
		return "board/co";
	}

	@GetMapping("mo")
	public String list3(Model model, String pageNum, HttpServletRequest req,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word, Recipe vo) {

		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		int pageSize = 5; // 한 블럭에 보일 게시글 수

		// 게시글 검색
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		hm.put("pageStart", (currentPage - 1) * pageSize);
		hm.put("pageSize", pageSize);

		List<Recipe> list = recipeBoardService.getListmo(vo);
		int count = recipeBoardService.boardCount(hm);
		PageVO page = new PageVO(count, currentPage, pageSize);

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		model.addAttribute("field", field);
		model.addAttribute("word", word);
		return "board/mo";
	}

	@GetMapping("do")
	public String list4(Model model, String pageNum, HttpServletRequest req,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word, Recipe vo) {

		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		int pageSize = 5; // 한 블럭에 보일 게시글 수

		// 게시글 검색
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		hm.put("pageStart", (currentPage - 1) * pageSize);
		hm.put("pageSize", pageSize);

		List<Recipe> list = recipeBoardService.getListdo(vo);
		int count = recipeBoardService.boardCount(hm);
		PageVO page = new PageVO(count, currentPage, pageSize);

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		model.addAttribute("field", field);
		model.addAttribute("word", word);
		return "board/do";
	}

	@GetMapping("rankpage")
	public String Rankpage(Recipe vo, Model model) {
		List<Recipe> list = recipeBoardService.getListRank(vo);
		model.addAttribute("list", list);

		return "board/rankpage";
	}
}
