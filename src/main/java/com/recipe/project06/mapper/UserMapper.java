package com.recipe.project06.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.project06.entity.User_Master;



@Mapper
public interface UserMapper {
	public List<User_Master> getList();
	public User_Master read(String user_id);
	public void join(User_Master user);
	public int idCheck(String user_id);
	public User_Master loginCheck(String user_id);
	public void modify(User_Master user);
	public void delete(String user_id);
	public List<User_Master> findAll(HashMap<String, Object> hm);
	public int userCount(HashMap<String, Object> hm);

}
