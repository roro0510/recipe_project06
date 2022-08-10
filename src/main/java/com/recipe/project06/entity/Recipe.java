package com.recipe.project06.entity;

import java.util.Date;
import java.util.List; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
	private int ci_num;
	private String ci_writer;
	private Date ci_regdate;
	private String ci_title;
	private String ci_content;
	private String ci_explanation;
	private int ci_count; // 조회수
	private int ci_hit_count; // 추천수?
	private String ci_categories;
	private List<Recipe_File> fileList;
	private String file_save;
	private String save_folder;
	
	//이미지 3개를 위한 컬럼 추가
	private String img1;
	private String img2;
	private String img3;
	private String img1_savefolder;
	private String img2_savefolder;
	private String img3_savefolder;
	
	
}
