package com.winter.app.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PostVO {
	private Long userId;
	private Long id;
	private String title;
	private String body;
	
}
