package com.winter.app.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.winter.app.board.PostVO;
import com.winter.app.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityLogoutAdd implements LogoutHandler {
	
	// Admin Key 
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;
	
	
	// << addLogoutHandler(obj) -- 로그아웃 할 시, 해야 할 기능을 구성 >>
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		log.info("===== LOGOUT ADD =============");
		
		this.logoutForKakao(authentication);;	
	}
	
	
	public void logoutForKakao(Authentication authentication) {
		RestTemplate restTemplate = new RestTemplate();
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		log.info("-------AccessToken : {} ----------",memberVO.getAccessToken()); // 각
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		// 요청: 액세스 토큰 방식 [1.ver]
		//headers.add("Authorization", "Bearer "+memberVO.getAccessToken());
		
		// 요청: 서비스 앱 어드민 키 방식 [2.ver]
		headers.add("Authorization", "KakaoAK 7e5cdcf168e4edf5f0b23a21bba922fd");	
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("target_id_type", "user_id");
		params.add("target_id", memberVO.getName());
		
		
		// 문서 > 카카오로그인 > REST API 
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params , headers);
		ResponseEntity<String> res = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/logout", req, String.class); // JSON이기에 retrun String
		
		String result = res.getBody();
		
		log.info("========로그아웃 id {}=============", result);
	}
	
	
	
	public void userRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		
		// parameter (POST 방식 일때 key, value 넣기)
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("postId", "1");
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params, null);

//		[get방식 결과가 1개일때.]
//		ResponseEntity<PostVO> res = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1" , PostVO.class, req);
//		// 응답내용 body에 있음. ResponseEntity<String>이어서 return String
//		PostVO result = res.getBody();
		
//		[post방식 결과가 여러개 일때.]		
//		List<PostVO> res = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/" , List.class, req);
	
		// parameter (GET 방식 일때 key, value 넣기-> url 뒤에 붙이기)
		ResponseEntity<String> res = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/comments?postId=1" , String.class, req);
		
		log.info("result ============> : {}", res);
		
		}
	
}
