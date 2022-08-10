package com.recipe.project06.service;

import java.util.HashMap;
import java.util.List;

import com.recipe.project06.entity.Board;
import com.recipe.project06.entity.Recipe;

public interface BoardService {
	 List<Board> getList();
	List<Board> findPage(HashMap<String,Object> hm);
	Board read(int b_num);
	void insert(Board board);
	int boardCount();
	boolean update(Board board);
	boolean delete(int b_num);
	List<Board> findAll(HashMap<String, Object> hm);
	int boardCount(HashMap<String, Object> hm);
}
