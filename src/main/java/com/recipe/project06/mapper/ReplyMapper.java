package com.recipe.project06.mapper;

import java.util.List;

import com.recipe.project06.entity.Recipe_Review;


public interface ReplyMapper {
	public int insert(Recipe_Review vo);
	public Recipe_Review read(int ci_re_num);
	public int update(Recipe_Review vo);
	public int delete(int ci_re_num);
	public List<Recipe_Review> getReplyList(int ci_num);
}
