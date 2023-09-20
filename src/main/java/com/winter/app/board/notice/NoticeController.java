package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
	
	@GetMapping("detail")
	public String gerDetail(NoticeVO noticeVO, Model model)throws Exception{
		noticeVO = (NoticeVO)noticeService.getDetail(noticeVO);
		model.addAttribute("detail",noticeVO);
		return "board/detail";
	}
	
	
	@GetMapping("add")
	public String add()throws Exception{
		return "board/add";
	}
	@PostMapping("add")
	public String add(NoticeVO noticeVO, MultipartFile[] files)throws Exception{
		// log.info("NoticeVO : {}", noticeVO); // lombok @ToString으로 console에 입력내용 출력
		
		//log.info("files : {} ", files);
		int result = noticeService.add(noticeVO, files);
		
		return "redirect:./list";
	}
	
	@GetMapping("update")
	public String setUpdate()throws Exception{
		return "board/update";
	}
	@PostMapping("update")
	public String setUpdate(NoticeVO noticeVO)throws Exception{
		int result = noticeService.setUpdate(noticeVO);
		
		return "redirect:./list";
	}
	
	@PostMapping("delete")
	public String setDelete(NoticeVO noticeVO)throws Exception{	
		return "redirect:./list";
	}
	
	
}
