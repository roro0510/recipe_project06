package com.recipe.project06.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe_File {
	private int ci_num;
	private int file_num;
	private String file_origin;
	private String file_save;
	private String file_type;
	private String save_folder;
	private String file_size;
	
	
}
