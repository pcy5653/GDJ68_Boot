package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j  // lombok 사용 : log -> sysout
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("list")
	public String gerList(Pager pager, Model model)throws Exception{
		List<BoardVO> ar = noticeService.getList(pager);
		model.addAttribute("list", ar);
		
		// ERROR, WARN, INFO, DEBUG, TRACE
		log.warn("getList 실행");
		return "board/list";
	}
}
