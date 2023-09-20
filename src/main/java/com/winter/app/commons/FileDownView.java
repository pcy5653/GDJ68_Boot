package com.winter.app.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.winter.app.board.FileVO;

import lombok.extern.slf4j.Slf4j;

@Component // 객체생성 | 생성된 객체이름 : fileDownView (첫글자 소문자)
@Slf4j
public class FileDownView extends AbstractView {

	@Value("${app.upload}")
	private String filePath;
	
	// 파일 다운 실행하는 메서드
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// <<서버에서 파일을 찾아서 client로 전송>>
		
		// 1. 어디에?  
		// filePath : pcy/upload/ => properties
		String board = (String)model.get("board");   // board = notice (Controller 맨위 설정)
		
		// 2. 어떤 파일?
		FileVO fileVO = (FileVO)model.get("fileVO"); // fileVO 에 담아 getFileDown 실행한 값.
		
		File file = new File(filePath+board, fileVO.getFileName());
		
		// 한글 처리
		response.setCharacterEncoding("UTF-8");
		
		// 파일의 크기 정보
		response.setContentLengthLong(file.length());
		
		// 다운로드시 파일의 이름을 인코딩
		String downName = URLEncoder.encode(fileVO.getOriginalName(), "UTF-8");
		
		// Header 설정
		response.setHeader("Content-Disposition", "attachment;filename=\""+downName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		// 하드디스크에서 파일 읽어서
		FileInputStream fi = new FileInputStream(file);
		
		// client에게 전송
		OutputStream os = response.getOutputStream();
		
		FileCopyUtils.copy(fi, os);
		
		// 자원 해제
		os.close();
		fi.close();
		
		
		log.info("---------File Down----------");
		log.info("board : {} ", board);
	}
}
