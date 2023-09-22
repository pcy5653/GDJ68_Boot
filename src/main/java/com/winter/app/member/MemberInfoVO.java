package com.winter.app.member;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberInfoVO {
	
	@NotBlank
	private String name;
	@Email
	private String email;
	@Past    // 과거 날짜만 가능
	private Date birth;
	private Date joinDate;
}
