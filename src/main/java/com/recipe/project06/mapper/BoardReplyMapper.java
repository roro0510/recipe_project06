package com.recipe.project06.mapper;

import java.util.List;

import com.recipe.project06.entity.Board_Review;
import com.recipe.project06.entity.Recipe_Review;


public interface BoardReplyMapper {
	public int insert1(Board_Review vo);
	public Board_Review read1(int b_re_num);
	public int update1(Board_Review vo);
	public int delete1(int b_re_num);
	public List<Board_Review> getReplyList1(int b_num);
}
