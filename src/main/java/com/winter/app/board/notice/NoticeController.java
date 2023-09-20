package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j  // lombok 사용 : log -> sysout
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	// fileDown 시에 어떤 위치인지 나타냄.
	// board이름으로 notice 값이 들어감.
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	
	
	@GetMapping("list")
	public String gerList(Pager pager, Model model)throws Exception{
		List<BoardVO> ar = noticeService.getList(pager);
		model.addAttribute("list", ar);
		
		// ERROR, WARN, INFO, DEBUG, TRACE
		log.warn("getList 실행");
		return "board/list";
	}
	
	@GetMapping("detail")
	public String getDetail(NoticeVO noticeVO, Model model)throws Exception{
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
	
	
	
	// file 부분
	
	// 1. fileDown
	@GetMapping("fileDown")
	public String getFileDown(FileVO fileVO, Model model)throws Exception{
		fileVO = noticeService.getFileDetail(fileVO);
		model.addAttribute("fileVO", fileVO);
		return "fileDownView";
	}
}
