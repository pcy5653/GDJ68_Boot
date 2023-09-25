package com.winter.app.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	// login 대체
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 필터에서 골라냄
		MemberVO memberVO = new MemberVO();
		log.info("======== 로그인 시도중 ===========");
		memberVO.setUsername(username);
		try {
			memberVO = memberDAO.getMember(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			memberVO = null;
		}
		return memberVO;
	}
	
	// login
//	public MemberVO getLogin(MemberVO memberVO)throws Exception{  // 매개변수 : 입력한 id, pw
//		MemberVO loginVO = memberDAO.getMember(memberVO);  // DB의 user의 정보와 일치한지 검증
//		
//		if(loginVO == null) {
//			return loginVO;   // login 실패
//		}
//		
//		if(loginVO.getPassword().equals(memberVO.getPassword())) {
//			return loginVO;	// login 성공
//		}	
//		return null;   
//	}
//	
	
	
	
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
	
	
	
	// 회원가입
	@Transactional(rollbackFor = Exception.class)  // int 2번하는데 둘중하나 오류뜨면 둘다 실패 -> rollback | Service Class 전체에 Annotation 해도 됨.
	public int setJoin(MemberVO memberVO)throws Exception{
		// pw 암호화 Encoding
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		
		int result = memberDAO.setJoin(memberVO); // member Table insert (비번 암호화 확인)
		Map<String, Object> map = new HashMap<>();
		map.put("roleNum", 3);					// Role = 3 (Member)
		map.put("username", memberVO.getUsername());
		result = memberDAO.setMemberRole(map);    // member_role table insert (회원가입 시, 권한 3(member) 확인)
		
		return result;
	}
}
