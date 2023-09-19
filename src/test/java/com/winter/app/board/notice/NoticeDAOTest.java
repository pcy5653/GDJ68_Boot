package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

@SpringBootTest
class NoticeDAOTest {
	@Autowired
	private NoticeDAO noticeDAO;
	
	//@Test
	void addTest()throws Exception{
		for(int i=0;i<100;i++) {
			BoardVO boardVO = new BoardVO();
			boardVO.setBoardTitle("title"+i);
			boardVO.setBoardWriter("writer"+i);
			boardVO.setBoardContents("contents"+i);
			int result = noticeDAO.add(boardVO);
			
			// 과부화 방지를 위한 잠깐 쉬는 코드
			if(i%10==0) { // 10개씩 넣고 쉬기
				Thread.sleep(500); 			
			}
		}
		System.out.println("finish");
	}
	
	//@Test
	void getCount()throws Exception{
		Pager pager = new Pager();
		pager.setKind("1");
		pager.setSearch("20");
		Long count = noticeDAO.getCount(pager);
		assertEquals(4L, count);
	}
	
	//@Test
	void getListTest()throws Exception {
		Pager pager = new Pager();
		pager.setStartRow(0L);
		pager.setLastRow(10L);
		pager.setKind("1");
		pager.setSearch("20");
		
		List<BoardVO> ar = noticeDAO.getList(pager);
		assertEquals(4, ar.size());	// ar.size가 0이 아니엿으면 좋겟다.
	}
	
	//@Test
	void getDetailTest()throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardNo(1L);
		assertEquals(0, boardVO);
	}

}
