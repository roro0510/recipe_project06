package com.recipe.project06.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.project06.entity.Board_Review;
import com.recipe.project06.entity.Recipe_Review;
import com.recipe.project06.mapper.BoardMapper;
import com.recipe.project06.mapper.BoardReplyMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardReplyServiceImpl implements BoardReplyService{
	private BoardReplyMapper boardReplyMapper;
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public int register1(Board_Review vo) {
		// TODO Auto-generated method stub
		boardMapper.replyCountUpdate(vo.getB_num(), 1);
		return boardReplyMapper.insert1(vo);
	}

	@Override
	public Board_Review read1(int b_re_num) {
		// TODO Auto-generated method stub
		return boardReplyMapper.read1(b_re_num);
	}

	@Override
	public int modify1(Board_Review vo) {
		// TODO Auto-generated method stub
		return boardReplyMapper.update1(vo);
	}

	@Transactional
	@Override
	public int remove1(int b_re_num) {
		// TODO Auto-generated method stub
		Board_Review vo=boardReplyMapper.read1(b_re_num);
		boardMapper.replyCountUpdate(vo.getB_num(), -1);
		return boardReplyMapper.delete1(b_re_num);
	}

	@Override
	public List<Board_Review> getList1(int b_num) {
		// TODO Auto-generated method stub
		return boardReplyMapper.getReplyList1(b_num);
	}

}
