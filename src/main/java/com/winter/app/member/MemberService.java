package com.winter.app.member;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	
	
	public void testValid (@Valid MemberVO memberVO, BindingResult bindingResult)throws Exception{
		log.info("Test Valid");
	}
	
	
	// login
	public MemberVO getLogin(MemberVO memberVO)throws Exception{  // 매개변수 : 입력한 id, pw
		MemberVO loginVO = memberDAO.getMember(memberVO);  // DB의 user의 정보와 일치한지 검증
		
		if(loginVO == null) {
			return loginVO;   // login 실패
		}
		
		if(loginVO.getPassword().equals(memberVO.getPassword())) {
			return loginVO;	// login 성공
		}	
		return null;   
	}
	
	
	
	
	// Member 검증 메서드
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult)throws Exception {
		// false : error X (검증통과) | true : error O (검증실패)
		boolean check = false;
		
		
		// 1. password 일치 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check = true;  //check=!check;
			
			// reject ("VO명", "properties에 적힐 키이름")
			bindingResult.rejectValue("passwordCheck", "memberVO.password.equalCheck");
		}
		
		
		// 2. ID 중복 검사
		MemberVO checkVO = memberDAO.getMember(memberVO);
		
		if(checkVO != null) {
			// null이 아니라면 중복 X
			check = true; // true : 중복발생
			bindingResult.rejectValue("username", "memberVO.username.equalCheck");
		}
		
		return check;
	}
}
