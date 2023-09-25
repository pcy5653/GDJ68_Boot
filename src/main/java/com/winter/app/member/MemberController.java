package com.winter.app.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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

	@Autowired
	private MemberService memberService;
	

	
	// 정보수정 update : DB or session에서 사용자 정보꺼내기 (password는 따로 수정)
	@GetMapping("update") // 정보수정 전
	public void setUpdate(HttpSession session, Model model)throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		//memberVO = memberService.getLogin(memberVO); // 가져온 정보가 일치 확인 후에 update 부분에 뿌리기
		
		MemberInfoVO memberInfoVO = new MemberInfoVO();
		memberInfoVO.setName(memberVO.getName());
		memberInfoVO.setBirth(memberVO.getBirth());
		memberInfoVO.setEmail(memberVO.getEmail());
		
		// form의 modelAttribute 이름과 동일하게 키이름을 맞춰야한다.
		model.addAttribute("memberInfoVO", memberInfoVO);
	}
	@PostMapping("update") // 정보수정 후 | 검증 (@Valid , BindingResult)
	public void setUpdate(@Valid MemberInfoVO memberInfoVO, BindingResult bindingResult)throws Exception{
		
	}
	
	
	
	
	// logout
	@GetMapping("logout")
	public String getLogout(HttpSession session)throws Exception{
		session.invalidate();  // 세션 무효화
		
		return "redirect:../";
	}
	

	
 
//	// login
	@GetMapping("login")
	public void getLogin(@ModelAttribute MemberVO memberVO)throws Exception{
		
	}
	
/**
  * <<Spring Security 할일 => login.post 부분 (SecurityConfig)>>
  * */ 
//	@PostMapping("login")
//	public String getLogin2(MemberVO memberVO, HttpSession session)throws Exception{
//		memberService.getLogin(memberVO);
//		
//		if(memberVO != null) {
//			session.setAttribute("member", memberVO);
//			return "redirect:../";  // 성공
//		}
//		return "redirect:./";       // 실패 => 현재위치
//	}
	
	
	
	// join
//  1. 입력 폼이 있는 JSP로 이동 전 Controller에서 Data가 없는 빈 VO객체를 Model 담아서 전송
//	@GetMapping("join") => 1.ver
//	public void setJoin(Model model)throws Exception{
//		MemberVO memberVO = new MemberVO();
//		model.addAttribute("memberVO", memberVO);
//	}
	@GetMapping("join") // => 2.ver
	public void setJoin(@ModelAttribute MemberVO memberVO)throws Exception{
		// Spring form 태그 사용시 빈 태그를 꼭 보내줘야 한다. <form:form>
		// @ModelAttribute : MemberVO를 model에 담아라. = (model.addAttribute("memberVO", memberVO))
		
	}
	@PostMapping("join")
	// parameter 사진이름 : photo
	// @Valid : 검증
	public String setJoin(@Valid MemberVO memberVO,BindingResult bindingResult, MultipartFile photo)throws Exception{
		
		// password 검증 => false : error X | true : error O
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		
		
		// hasErrors가 발생하거나 check가 true면 (오류발생) return 값 실행.
		if(bindingResult.hasErrors() || check) {
			// 빈칸이면 join에 에러 출력 <form:errors>
			return "member/join";
		}
		
		// 회원가입 진행 (pw암호화, service에서 int 2번으로 transaction 실행.)
		int result = memberService.setJoin(memberVO); 
		
		log.info("photo : {} ---- size : {} ", photo.getOriginalFilename(), photo.getSize());
		return "redirect:../";
	}
}
