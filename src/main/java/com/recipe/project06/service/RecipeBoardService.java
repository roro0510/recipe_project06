package com.recipe.project06.service;

import java.util.HashMap;

import java.util.List;

import com.recipe.project06.entity.Recipe;
import com.recipe.project06.entity.Recipe_File;

public interface RecipeBoardService {
	public List<Recipe> getList();
	List<Recipe> findPage(HashMap<String,Object> hm);
	Recipe read(int ci_num);
	void insert(Recipe recipe);
	int boardCount();
	boolean update(Recipe recipe);
	boolean delete(int ci_num);
	public List<Recipe> findAll(HashMap<String, Object> hm);
	public int boardCount(HashMap<String, Object> hm);
	public List<Recipe> totalfindAll(HashMap<String, Object> hm);
	public int totalboardCount(HashMap<String, Object> hm);
	Recipe_File getFile(int file_num);
	List<Recipe> getListko(Recipe vo);
	List<Recipe> getListjo(Recipe vo);
	List<Recipe> getListco(Recipe vo);
	List<Recipe> getListmo(Recipe vo);
	List<Recipe> getListdo(Recipe vo);
	List<Recipe> getListRank(Recipe vo);
	List<Recipe> getListTop4(Recipe vo);
}
