package com.winter.app.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.winter.app.board.BoardVO;
import com.winter.app.board.notice.NoticeDAO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

/**
 * 특정 시간마다 실행 (생일자에게 문자전송, 1년 계정 알림 등..매일 00시에 진행)
 * */

@Component
@Slf4j
public class TestScheduler {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	// 특정 주기로 반복 (1초)
	//@Scheduled(fixedRateString = "2000") // == (fixedRate = 2000)
	public void userFixRate()throws Exception{
		// 메서드의 종료와 상관없이 2초마다 실행
		log.info("============ Fixed Rate ============");
	}

	
	//@Scheduled(fixedDelay = 1000)
	public void useFixedDelay()throws Exception{
		// 메서드가 반드시 종료 후에 다시 시작.
		log.info("============ Fixed Delay ============");
	}
	
	
	@Scheduled(cron = "*/10 *  *  *  *  *")
	public void useCron()throws Exception{
		log.info("============ Cron =============");
		
		Pager pager = new Pager();
		List<BoardVO> ar = noticeDAO.getList(pager);
		log.info("List : {} ", ar);
	}
	
	
}
