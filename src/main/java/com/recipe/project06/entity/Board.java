package com.recipe.project06.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int b_num; // 게시글 번호 bno
	private String b_writer;
	private Date b_regdate;
	private String b_title;
	private String b_content;
	private int b_count;
	

	
	
}
