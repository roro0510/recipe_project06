package com.recipe.project06.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_Master {
	private String user_id;
	private String user_pass;
	private String user_email;
	private String user_name;
	private String addr1;
	private String addr2;
	private String addr3;
}
