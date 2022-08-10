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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.recipe.project06.entity.Board;
import com.recipe.project06.entity.PageVO;
import com.recipe.project06.entity.Recipe;
import com.recipe.project06.entity.Recipe_File;
import com.recipe.project06.service.BoardService;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	BoardService boardService;

	@GetMapping("freeregister")
	public void register() {

	}
//	@PostMapping("insert")
//	public String registr(BoardVO vo) {
//		boardService.insert(vo);
//		return "redirect:/board/list";
//	}

	@PostMapping("insert1")
	public String register(Board board, MultipartFile[] uploads, HttpSession session) {
		String uploadFolder = session.getServletContext().getRealPath("/resources/upload");

		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadFolder + File.separator + today;
		System.out.println(saveFolder);
		File folder = new File(saveFolder);

		if (!folder.exists()) {
			folder.mkdirs();
		}
		List<Recipe_File> fileList = new ArrayList<Recipe_File>();

		for (MultipartFile multipartFile : uploads) {
			String originFile = multipartFile.getOriginalFilename();

			if (!originFile.isEmpty()) {
				Recipe_File recipe_File = new Recipe_File();
				UUID uuid = UUID.randomUUID();
				String saveFileName = uuid.toString() + "_" + originFile;
				String fileType = multipartFile.getContentType();
				log.info("fileType:" + fileType);
				fileType = fileType.substring(0, fileType.indexOf("/"));
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
		}
		boardService.insert(board);

		return "redirect:/board/free";

	}

	@GetMapping("update1/{b_num}")
	public String update(@PathVariable("b_num") int b_num, Model model) {
		model.addAttribute("board", boardService.read(b_num));
		return "board/freeupdate";
	}

	@PostMapping("update1")
	public String update(Board vo) {
		boardService.update(vo);
		return "redirect:/board/free";
	}

	@GetMapping("delete1/{b_num}")
	public String delete(@PathVariable("b_num") int b_num) {
		boardService.delete(b_num);
		return "redirect:/board/free";
	}

	@GetMapping("free")
	public String list(Model model, String pageNum, @RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word) {
		log.info("listController");

		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		System.out.println(currentPage);

		int pageSize = 3;

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		hm.put("pageStart", (currentPage - 1) * pageSize);
		hm.put("pageSize", pageSize);

		List<Board> list = boardService.findAll(hm);
		int count = boardService.boardCount(hm);
		PageVO page = new PageVO(count, currentPage, pageSize);

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		model.addAttribute("field", field);
		model.addAttribute("word", word);
		return "board/free";
	}

	@GetMapping("freedetail")
	public String read(@RequestParam("b_num") int b_num, String pageNum,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word, Model model) {
		model.addAttribute("board", boardService.read(b_num));
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("field", field);
		model.addAttribute("word", word);
		return "board/freedetail";
	}

}
