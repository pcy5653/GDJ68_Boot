package com.winter.app.member;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	
	
	
//  1. 입력 폼이 있는 JSP로 이동 전 Controller에서 Data가 없는 빈 VO객체를 Model 담아서 전송 (GET join과 동일한 방법)
//	@GetMapping("join")
//	public void setJoin(Model model)throws Exception{
//		MemberVO memberVO = new MemberVO();
//		model.addAttribute("memberVO", memberVO);
//	}
	@GetMapping("join")
	public void setJoin(@ModelAttribute MemberVO memberVO)throws Exception{
		// @ModelAttribute : MemberVO를 model에 담아라. = (model.addAttribute("memberVO", memberVO))
		
	}
	
	@PostMapping("join")
	// parameter 사진이름 : photo
	public String setJoin(@Valid MemberVO memberVO,BindingResult bindingResult, MultipartFile photo)throws Exception{
		if(bindingResult.hasErrors()) {
			// 빈칸이면 join에 에러 출력 <form:errors>
			return "member/join";
		}
		
		log.info("photo : {} ---- size : {} ", photo.getOriginalFilename(), photo.getSize());
		return "redirect:../";
	}
}
