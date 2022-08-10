package com.recipe.project06.mapper;

import java.util.HashMap;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.recipe.project06.entity.Recipe;
import com.recipe.project06.entity.Recipe_File;






@Mapper
public interface RecipeMapper {
	//@Select("select * from tbl_board where bno>0")
	public List<Recipe> getList(); // 게시글 목록
	public List<Recipe> findPage(HashMap<String,Object> hm);
	public void insert(Recipe recipe); // 글쓰기
	public int insertSelectKey(Recipe recipe);
	public Recipe read(int ci_num); // 게시글 읽음
	public int update(Recipe recipe); // 게시글 수정
	public int delete(int ci_num); // 게시글 삭제
	public int boardCount();
	public int hitCount(int ci_num); // 조회수 메소드
	public int boardCount(HashMap<String, Object> hm);
	public List<Recipe> findAll(HashMap<String, Object> hm);
	public void replyCountUpdate(@Param("ci_num") int ci_num,@Param("amount") int amount); 
	public List<Recipe_File> getFileList(int bno); // 해당 게시물에 파일을 받아오는 메소드
	public int fileRegister(Recipe_File recipefile);
	public Recipe_File getFile(int ci_num); // 하나의 파일을 받아온다네
	public List<Recipe> getListko(Recipe vo);
	public List<Recipe> getListjo(Recipe vo);
	public List<Recipe> getListco(Recipe vo);
	public List<Recipe> getListmo(Recipe vo);
	public List<Recipe> getListdo(Recipe vo);
	public List<Recipe> getListRank(Recipe vo);
	public List<Recipe> totalfindAll(HashMap<String, Object> hm);
	public int totalboardCount(HashMap<String, Object> hm);
	public List<Recipe> getListTop4(Recipe vo);
	
}
