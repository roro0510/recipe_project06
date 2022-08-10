package com.recipe.project06.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.project06.entity.Board_Review;
import com.recipe.project06.entity.Recipe_Review;
import com.recipe.project06.service.BoardReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/replies/*")
@Log
@AllArgsConstructor
public class BoardReplyController {
	private BoardReplyService boardReplyService;

	@PostMapping(value = "/new1", consumes = "application/json")
	public ResponseEntity<String> create(@RequestBody Board_Review vo) {
		log.info("ReplyVO............" + vo);
		int insertCount = boardReplyService.register1(vo);
		log.info("Reply insert count:" + insertCount);
		return insertCount == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping("getList1/{b_num}")
	public ResponseEntity<List<Board_Review>> getList(@PathVariable("b_num") int b_num) {
		log.info("getList...................");
		return new ResponseEntity<List<Board_Review>>(boardReplyService.getList1(b_num), HttpStatus.OK);
	}

	@GetMapping(value = "/{b_re_num}")
	public ResponseEntity<Board_Review> get(@PathVariable("b_re_num") int b_re_num) {
		log.info("getReply(): " + b_re_num);
		return new ResponseEntity<Board_Review>(boardReplyService.read1(b_re_num), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{b_re_num}")
	public ResponseEntity<String> remove(@PathVariable("b_re_num") int b_re_num) {
		log.info("remove: " + b_re_num);
		return boardReplyService.remove1(b_re_num) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping(value = "/{b_re_num}", consumes = "application/json")
	public ResponseEntity<String> modify(@RequestBody Board_Review vo, @PathVariable("b_re_num") int b_re_num) {
		vo.setB_re_num(b_re_num);

		return boardReplyService.modify1(vo) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
