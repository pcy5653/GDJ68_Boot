package com.winter.app.board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString  // sysout에 참조변수를 찍으면 ToString(주소) 자동으로 불러오는데 주소가 아닌 내용을 찍기위해 annotation 한다.
           // console 창에 대입한 값들이 찍힘.
public class BoardVO {
	private Long boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContents;
	private Date boardDate;
	private Long boardHit;
}
