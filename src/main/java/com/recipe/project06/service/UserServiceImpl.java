package com.recipe.project06.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.project06.entity.User_Master;
import com.recipe.project06.mapper.UserMapper;



@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper usermapper;
	
	@Override
	public void join(User_Master user) {
		// TODO Auto-generated method stub
		usermapper.join(user);

	}

	@Override
	public int idCheck(String id) {
		// TODO Auto-generated method stub
		return usermapper.idCheck(id);
	}

	@Override
	public User_Master loginCheck(String user_id) {
		// TODO Auto-generated method stub
		return usermapper.loginCheck(user_id);
	}

	@Override
	public User_Master read(String id) {
		// TODO Auto-generated method stub
		return usermapper.read(id);
	}

	@Override
	public void modify(User_Master user) {
		usermapper.modify(user);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		usermapper.delete(id);
	}

	@Override
	public List<User_Master> getList() {
		// TODO Auto-generated method stub
		return usermapper.getList();
	}

	@Override
	public List<User_Master> findAll(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return usermapper.findAll(hm);
	}

	@Override
	public int userCount(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return usermapper.userCount(hm);
	}



}
