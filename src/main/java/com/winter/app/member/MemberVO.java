package com.winter.app.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//update 시 username, password, passwordCheck만 받기 위해 나머지 정보는 MemberInfoVO에 넣는다.
// memberVO를 UserDetails 타입으로 구현
public class MemberVO extends MemberInfoVO implements UserDetails {

	
	@NotBlank  // Null 허용하지 않음, 문자 1개이상 포함
	@Size(max=12, min=2)  // 문자열이나 배열의 길이 제한, index {0}:멤버변수명, {1}:max, {2}:min
	private String username;
	// 공백없이 숫자, 소문자, 특수기호 넣어서 6~12자리 비밀번호
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}", message="올바른 비빌번호를 입력하세요.")
	private String password;
	private String passwordCheck; // pw check
	
	// 1회원은 여러개의 권한(role)을 갖는다. roleVOs안에 있는 roleName을 리턴
	private List<RoleVO> roleVOs;
	
	
	
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
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
