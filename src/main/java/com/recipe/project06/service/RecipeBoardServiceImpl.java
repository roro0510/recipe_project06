package com.recipe.project06.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.project06.entity.Recipe;
import com.recipe.project06.entity.Recipe_File;
import com.recipe.project06.mapper.RecipeMapper;


@Service
public class RecipeBoardServiceImpl implements RecipeBoardService {
	@Autowired
	RecipeMapper recipemapper;

	@Override
	public List<Recipe> getList() {
		// TODO Auto-generated method stub
		return recipemapper.getList();
	}

	@Override
	public Recipe read(int ci_num) {
		// TODO Auto-generated method stub
		recipemapper.hitCount(ci_num);
		List<Recipe_File> fileList = recipemapper.getFileList(ci_num);
		Recipe recipe = recipemapper.read(ci_num);
		recipe.setFileList(fileList);
		return recipe;
	}

	@Override
	public void insert(Recipe recipe) {
		// TODO Auto-generated method stub
		
		// 만약 게시글이 비어있을 경우
		if (recipemapper.getList().isEmpty()) {
			recipemapper.insert(recipe);
		} else {
			// Mapper에서 SelectKey로 bno를 반환하기 때문에 BoardVO에서 bno를 사용할 수 있다.
			recipemapper.insertSelectKey(recipe);
		}

		for (Recipe_File recipefile : recipe.getFileList()) {
			recipefile.setCi_num(recipe.getCi_num());
			recipemapper.fileRegister(recipefile);
		}
	}

	@Override
	public int boardCount() {
		// TODO Auto-generated method stub
		return recipemapper.boardCount();
	}

	@Override
	public boolean update(Recipe recipe) {
		// TODO Auto-generated method stub
		return recipemapper.update(recipe) == 1;
	}

	@Override
	public boolean delete(int ci_num) {
		// TODO Auto-generated method stub
		return recipemapper.delete(ci_num) == 1;
	}

	@Override
	public List<Recipe> findPage(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return recipemapper.findPage(hm);
	}

	@Override
	public List<Recipe> findAll(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return recipemapper.findAll(hm);
	}

	@Override
	public int boardCount(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return recipemapper.boardCount(hm);
	}
	
	@Override
	public List<Recipe> totalfindAll(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return recipemapper.totalfindAll(hm);
	}

	@Override
	public int totalboardCount(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return recipemapper.totalboardCount(hm);
	}

	@Override
	public Recipe_File getFile(int file_num) {
		// TODO Auto-generated method stub
		return recipemapper.getFile(file_num);
	}

	@Override
	public List<Recipe> getListko(Recipe vo) {
		// TODO Auto-generated method stub
		return recipemapper.getListko(vo);
	}

	@Override
	public List<Recipe> getListjo(Recipe vo) {
		// TODO Auto-generated method stub
		return recipemapper.getListjo(vo);
	}

	@Override
	public List<Recipe> getListco(Recipe vo) {
		// TODO Auto-generated method stub
		return recipemapper.getListco(vo);
	}

	@Override
	public List<Recipe> getListmo(Recipe vo) {
		// TODO Auto-generated method stub
		return recipemapper.getListmo(vo);
	}

	@Override
	public List<Recipe> getListdo(Recipe vo) {
		// TODO Auto-generated method stub
		return recipemapper.getListdo(vo);
	}

	@Override
	public List<Recipe> getListRank(Recipe vo) {
		// TODO Auto-generated method stub
		return recipemapper.getListRank(vo);
	}

	@Override
	public List<Recipe> getListTop4(Recipe vo) {
		// TODO Auto-generated method stub
		return recipemapper.getListTop4(vo);
	}

}
