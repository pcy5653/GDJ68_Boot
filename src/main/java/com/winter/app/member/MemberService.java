package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	
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
