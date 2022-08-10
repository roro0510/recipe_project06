package com.recipe.project06.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.project06.entity.Board;
import com.recipe.project06.entity.Recipe;
import com.recipe.project06.entity.Recipe_File;
import com.recipe.project06.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<Board> getList() {
		// TODO Auto-generated method stub
		return boardMapper.getList();
	}

	@Override
	public Board read(int b_num) {
		// TODO Auto-generated method stub
		boardMapper.hitCount(b_num);
		
		Board board = boardMapper.read(b_num);
		
		return board;
	}

	@Override
	public void insert(Board board) {
		// TODO Auto-generated method stub
		
		// 만약 게시글이 비어있을 경우
		if (boardMapper.getList().isEmpty()) {
			boardMapper.insert(board);
		} else {
			// Mapper에서 SelectKey로 bno를 반환하기 때문에 BoardVO에서 bno를 사용할 수 있다.
			boardMapper.insertSelectKey(board);
		}

	
	}

	@Override
	public int boardCount() {
		// TODO Auto-generated method stub
		return boardMapper.boardCount();
	}

	@Override
	public boolean update(Board board) {
		// TODO Auto-generated method stub
		return boardMapper.update(board) == 1;
	}

	@Override
	public boolean delete(int b_num) {
		// TODO Auto-generated method stub
		return boardMapper.delete(b_num) == 1;
	}

	@Override
	public List<Board> findPage(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return boardMapper.findPage(hm);
	}

	@Override
	public List<Board> findAll(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return boardMapper.findAll(hm);
	}

	@Override
	public int boardCount(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return boardMapper.boardCount(hm);
	}
		
	

//	@Override
//	public Board read(int b_num) {
//		// TODO Auto-generated method stub
//		boardMapper.hitCount(b_num);
//		Board vo = boardMapper.read(b_num);
//		
//		return null;
//	}

}
