package com.winter.app.board.notice;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.winter.app.board.BoardDAO;

//@Repository
@Mapper    // DAO역할이기에 @Mapper 필수!
public interface NoticeDAO extends BoardDAO {
// NoticeDAO는 interface로 BoardDAO 상속
	
}
