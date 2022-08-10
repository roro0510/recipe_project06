package com.recipe.project06.service;

import java.util.HashMap;
import java.util.List;

import com.recipe.project06.entity.User_Master;

public interface UserService {
	public List<User_Master> getList();
	public void join(User_Master user);
	public int idCheck(String id);
	public User_Master loginCheck(String user_id);
	public User_Master read(String id);
	public void modify(User_Master user);
	public void delete(String id);
	public List<User_Master> findAll(HashMap<String, Object> hm);
	public int userCount(HashMap<String, Object> hm);

}
