package com.recipe.project06.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.project06.entity.Recipe_Review;
import com.recipe.project06.mapper.RecipeMapper;
import com.recipe.project06.mapper.ReplyMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	private ReplyMapper replyMapper;
	private RecipeMapper recipeMapper;
	
	@Transactional
	@Override
	public int register(Recipe_Review vo) {
		// TODO Auto-generated method stub
		recipeMapper.replyCountUpdate(vo.getCi_num(), 1);
		return replyMapper.insert(vo);
	}

	@Override
	public Recipe_Review read(int ci_re_num) {
		// TODO Auto-generated method stub
		return replyMapper.read(ci_re_num);
	}

	@Override
	public int modify(Recipe_Review vo) {
		// TODO Auto-generated method stub
		return replyMapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(int ci_re_num) {
		// TODO Auto-generated method stub
		Recipe_Review vo=replyMapper.read(ci_re_num);
		recipeMapper.replyCountUpdate(vo.getCi_num(), -1);
		return replyMapper.delete(ci_re_num);
	}

	@Override
	public List<Recipe_Review> getList(int ci_num) {
		// TODO Auto-generated method stub
		return replyMapper.getReplyList(ci_num);
	}

}
