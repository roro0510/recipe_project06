package com.recipe.project06.service;

import java.util.List;

import com.recipe.project06.entity.Recipe_Review;



public interface ReplyService {
	public int register(Recipe_Review vo);
	public Recipe_Review read(int ci_re_num);
	public int modify(Recipe_Review vo);
	public int remove(int ci_re_num);
	public List<Recipe_Review> getList(int ci_num);
}
