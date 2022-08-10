package com.recipe.project06.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.recipe.project06.entity.Board;
import com.recipe.project06.entity.Recipe;
import com.recipe.project06.entity.Recipe_File;

@Mapper
public interface BoardMapper {

	public List<Board> getList(); // 게시글 목록
	public List<Board> findPage(HashMap<String,Object> hm);
	public void insert(Board board); // 글쓰기
	public int insertSelectKey(Board recipe);
	public Board read(int b_num); // 게시글 읽음
	public int update(Board board); // 게시글 수정
	public int delete(int b_num); // 게시글 삭제
	public int boardCount();
	public int hitCount(int b_num); // 조회수 메소드
	public int boardCount(HashMap<String, Object> hm);
	public List<Board> findAll(HashMap<String, Object> hm);
	public void replyCountUpdate(@Param("b_num") int b_num,@Param("amount") int amount); 
	public List<Board> getFileList(int b_num); // 해당 게시물에 파일을 받아오는 메소드
//	public int fileRegister(Recipe_File recipefile);
//	public Recipe_File getFile(int ci_num); // 하나의 파일을 받아온다네
}
