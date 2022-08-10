package com.recipe.project06.service;

import java.util.List;

import com.recipe.project06.entity.Board_Review;
import com.recipe.project06.entity.Recipe_Review;



public interface BoardReplyService {
	public int register1(Board_Review vo);
	public Board_Review read1(int b_re_num);
	public int modify1(Board_Review vo);
	public int remove1(int b_re_num);
	public List<Board_Review> getList1(int b_num);
}
