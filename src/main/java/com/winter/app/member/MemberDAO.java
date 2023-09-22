package com.winter.app.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
// 알아서 override 해줌
	
	
	public MemberVO getMember(MemberVO memberVO)throws Exception;
	
}
