package com.winter.app.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileVO {
	
	private Long fileNum;
	private String fileName;
	private String originalName;
}
