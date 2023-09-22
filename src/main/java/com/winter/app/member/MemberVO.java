package com.winter.app.member;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {

	@NotBlank  // Null 허용하지 않음, 문자 1개이상 포함
	@Size(max=12, min=2)  // 문자열이나 배열의 길이 제한, index {0}:멤버변수명, {1}:max, {2}:min
	private String username;
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}", message="올바른 비빌번호를 입력하세요.")
	private String password;
	private String passwordCheck; // pw check
	@NotBlank
	private String name;
	@Email
	private String email;
	@Past    // 과거 날짜만 가능
	private Date birth;
	private Date joinDate;
}
