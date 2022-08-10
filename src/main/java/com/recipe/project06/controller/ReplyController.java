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

import com.recipe.project06.entity.Recipe_Review;
import com.recipe.project06.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/ci_replies/*")
@Log
@AllArgsConstructor
public class ReplyController {
private ReplyService replyService;
	
	@PostMapping(value="/new",consumes="application/json")
	public ResponseEntity<String> create(@RequestBody Recipe_Review vo){
		log.info("ReplyVO............"+vo);
		int insertCount=replyService.register(vo);
		log.info("Reply insert count:"+insertCount);
		return insertCount==1 ? new ResponseEntity<String>("success",HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping("getList/{ci_num}")
	public ResponseEntity<List<Recipe_Review>> getList(@PathVariable("ci_num") int ci_num){
		log.info("getList...................");
		return new ResponseEntity<List<Recipe_Review>>(replyService.getList(ci_num),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{ci_re_num}")
	public ResponseEntity<Recipe_Review> get(@PathVariable("ci_re_num") int ci_re_num) {
	log.info("getReply(): " + ci_re_num);
		return new ResponseEntity<Recipe_Review>(replyService.read(ci_re_num), HttpStatus.OK);
	}
	
	@DeleteMapping(value= "/{ci_re_num}")
	public ResponseEntity<String> remove(@PathVariable("ci_re_num") int ci_re_num) {
	log.info("remove: " + ci_re_num);
		return replyService.remove(ci_re_num) == 1 ? 
			new ResponseEntity<String>("success", HttpStatus.OK)
			: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping(value="/{ci_re_num}",consumes="application/json")
	public ResponseEntity<String> modify(@RequestBody Recipe_Review vo, 
			@PathVariable("ci_re_num") int ci_re_num){
		vo.setCi_re_num(ci_re_num);
		
		return replyService.modify(vo)==1 ? 
				new ResponseEntity<String>("success",HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
