package com.winter.app.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService extends DefaultOAuth2UserService implements UserDetailsService {

	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	

	// Social Login (카카오 로그인)
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("-================카카오 로그인 처리 진행==============");
		
		// 카카오의 접속 정보 (사용자 정보X)
		ClientRegistration clientRegistration = userRequest.getClientRegistration();
		log.info("========= clientRegistration : {} ========>>>>>>> ", clientRegistration);
		
		
		// 사용자의 정보 [ super.loadUser(userRequest) ]
		OAuth2User auth2User =  super.loadUser(userRequest);
		log.info("========= auth2User : {} ========>>>>>>> ", auth2User);
		
		// kakao처리
		String social = clientRegistration.getRegistrationId();   // 카카오
		if(social.equals("kakao")) {
			// 리턴타입 OAuth2User
			auth2User = this.forKakao(auth2User, userRequest);
		}
		
		// 사용자의 정보
		return auth2User;
	}
	// kakao처리 메소드 
	private OAuth2User forKakao(OAuth2User auth2User, OAuth2UserRequest userRequest){
		// MemberVO 타입 : UserDetails, OAuth2User
		MemberVO memberVO = new MemberVO();
		// memberVO.setUsername(null);
		
		
		/**
	  	Key :id				value : 1129858552 -고유 아이디
		Key :connected_at	value : 2019-07-22T03:05:22Z 
		Key :properties		value : {nickname=김대기}
		Key :kakao_account	value : {profile_needs_agreement=false, profile={nickname=김대기, thumbnail_image_url=http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_110x110.jpg, profile_image_url=http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg, is_default_image=true}, has_email=true, email_needs_agreement=false, is_email_valid=true, is_email_verified=true, email=kimdaeki@gmail.com}
		 */
		
		// auth2User.getAttribute("properties").getClass();  --> class type 확인 [ java.util.LinkedHashMap ] => 리턴타입을 알기 위함.
		// auth2User.getAttribute("properties");  --> 사용자의 정보 <키 : [nickname, profile_image, thumbnail_image]>
		LinkedHashMap<String, String> map = auth2User.getAttribute("properties");		
		log.info("===> auth2User = properties : {} ==>>>>>>", map);

		LinkedHashMap<String, Object> kakaoAccount = auth2User.getAttribute("kakao_account");
		LinkedHashMap<String, Object> profile = (LinkedHashMap<String, Object>)kakaoAccount.get("profile");    // Object 타입을 LinkedHashMap으로 변경
		log.info("===> auth2User = kakao_account : {} ==>>>>>>", profile.get("nickname"));
		log.info("===> auth2User = kakao_account : {} ==>>>>>>", profile.get("profile_image_url"));
		log.info("===> auth2User = kakao_account : {} ==>>>>>>", kakaoAccount.get("email"));
		log.info("===> auth2User = kakao_account : {} ==>>>>>>", kakaoAccount.get("birthday"));    // birth는 '-'로 년,월,일 구분 (무조건 년,월,일 넣어서 해야한다.)
		
		
		// ++플젝 : 사용자가 DB에 있는지 조회
		// << 사용자 정보 : username, name, email 구하기 >>
		memberVO.setAccessToken(userRequest.getAccessToken().getTokenValue());
		memberVO.setUsername(map.get("nickname")); // LinkedHashMap<String, Object> -> LinkedHashMap<String, String> 으로 하면 .toString() 사용 X 
		//memberVO.setName(map.get("nickname"));
		memberVO.setName(auth2User.getName());     // 카카오 회원Id를 Name에 대입
		// memberVO.setUsername(profile.get("nickname").toString()); <위 코드와 동일 / map에서 꺼내거나 profile에서 꺼내거나 둥중 하나>
		memberVO.setEmail(kakaoAccount.get("email").toString());
		
		
		// << 사용자 정보 : birth 구하기 >>
		//memberVO.setBirth(kakaoAccount.get("birthday").toString());      // 문자열(birthday) -> 날짜(Date) 변경
		// birth는 '-'로 년,월,일 구분 (무조건 년,월,일 넣어서 해야한다.) -> 출력된 것은 String 타입이기에 index로 2자리씩 나눠서 출력
		
		String birth = kakaoAccount.get("birthday").toString();	// 0518
		String mon = birth.substring(0,2); //05
		String day = birth.substring(2);  //18
		// >> 1. 년도를 현재 시간으로 설정 > Calendar 사용 || 날짜를 다루는 것 = Calendar
		Calendar ca = Calendar.getInstance(); 
		int y = ca.get(Calendar.YEAR);
		// >> 2. + (더하기, 자식만들기)를 하는 변수
		StringBuffer sb = new StringBuffer();
		sb.append(y).append("-").append(mon).append("-").append(day);
	
		log.info("Date Birth : {} =============> "+Date.valueOf(sb.toString()));
		memberVO.setBirth(Date.valueOf(sb.toString()));   // 결과 >> Calendar과 StringBuffer()을 사용하여 현재 년도를 기준으로 년,월,일 출력
		
		
		// << 꺼낸 정보를 다시 memberVO에 넣기(백업용) >>
		memberVO.setAttributes(auth2User.getAttributes()); 
		
		
		// ++플젝 : 사용자 권한을 DB에서 조회
		// Social 로그인 시, 권한(role)이 null이기에 작성 (수동)
		List<RoleVO> list = new ArrayList<>();   // 1. memberVO의 roleVOs 타입 동일
		RoleVO roleVO = new RoleVO();			 // 2. 객체생성
		roleVO.setRoleName("ROLE_MEMBER");		 // 3. role의 권한 부여	
		
		list.add(roleVO);						 // 4. list에 담기	
		
		memberVO.setRoleVOs(list);				 // 5. memberVO에 Social 로그인 사용자의 권한 부여	
		
		
		return memberVO;
	}
	
	
	// Server Login	(일반 로그인)
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
