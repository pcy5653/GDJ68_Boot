package com.winter.app.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//update 시 username, password, passwordCheck만 받기 위해 나머지 정보는 MemberInfoVO에 넣는다.
// memberVO를 UserDetails 타입으로 구현
public class MemberVO extends MemberInfoVO implements UserDetails, OAuth2User {

	@NotBlank  // Null 허용하지 않음, 문자 1개이상 포함
	@Size(max=12, min=2)  // 문자열이나 배열의 길이 제한, index {0}:멤버변수명, {1}:max, {2}:min
	private String username;
	// 공백없이 숫자, 소문자, 특수기호 넣어서 6~12자리 비밀번호
	//@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}", message="올바른 비빌번호를 입력하세요.")
	private String password;
	private String passwordCheck; // pw check
	
	// 1회원은 여러개의 권한(role)을 갖는다. roleVOs안에 있는 roleName을 리턴
	private List<RoleVO> roleVOs;
	
	// boolean 타입 getter의 이름은 "is"로 시작. <휴먼계정 표시 | [true : 1 / false : 0]>
	private Boolean enabled;
	
	// OAuth2User 객체 생성
	private Map<String, Object> attributes;
	
	
	// ------ OAuth2User Override
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return this.attributes;
	}
	
	
	// ------ UserDetails Override
	
	// getAuthorities : 권한의 정보들, roleVOs에 있는 권한의 정보들을 꺼내는 것.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Collection 자식 : List, Set
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVO : roleVOs) {
			// List인 authorities를 새로운 객체를 만들어(new) roleName을 꺼내 1개씩 돌린다.
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		
		
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		// SecurityFailHandler에서 AccountExpiredException(계정 기간) | [true O / false X]
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// SecurityFailHandler에서 LockedException(잠긴 계정) | [true O / false X]
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// SecurityFailHandler에서 CredentialsExpiredException(비밀번호 만료) | [true O / false X]
		return true;
	}
	@Override
	public boolean isEnabled() {
		// SecurityFailHandler에서 DisabledException(휴먼 계정) | [true : 1 / false : 0]
		
		// column의 값을 return
		return this.enabled;
	}
	
}
