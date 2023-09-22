package com.winter.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect 	// 핵심로직에서 공통로직이 사용될 순서를 정함.
public class Card {
	
	// advice : 공통로직
	//@Before("execution(* com.winter.app.aop.Transfer.*())")
	//@After("execution(* com.winter.app.aop.Transfer.*())")
	@Around("execution(* com.winter.app.aop.Transfer.*())")
	public Object cardCheck(ProceedingJoinPoint joinPoint)throws Throwable{
		log.info("=======================================");
		log.info("Card Check 타기");
		
		
		Object[] args = joinPoint.getArgs();
		log.info("Args : {} ", args);
		
		Object obj = joinPoint.proceed();   // @Around 했을 시 사용. 매개변수 ProceedingJoinPoint 선언. joinPoint = point-cut 
		
		log.info("Card Check 내리기");
		log.info("=======================================");
		
		return obj;
	}
}
