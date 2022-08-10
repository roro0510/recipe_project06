package com.recipe.project06.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board_Review {
	private int b_num; //bno
	private int b_re_num; //rno
	private String b_re_writer; // 댓글 작성자
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") // replydate를 날짜형식으로 변환한다. 표준시간대를 서울로 설정한다.
	private Date b_re_regdate; //댓글 일자
	private String b_re_content; //댓글 냉용
}
