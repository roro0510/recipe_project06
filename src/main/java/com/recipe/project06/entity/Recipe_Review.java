package com.recipe.project06.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe_Review {
	private int ci_num;
	private int ci_re_num;
	private String ci_re_writer;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") // replydate를 날짜형식으로 변환한다. 표준시간대를 서울로 설정한다.
	private Date ci_re_regdate;
	private String ci_re_content;
}
