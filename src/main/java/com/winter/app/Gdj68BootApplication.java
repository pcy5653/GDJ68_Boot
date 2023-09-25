package com.winter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAspectJAutoProxy //AOP 사용 : 선언(생략 가능)
@SpringBootApplication
@EnableScheduling		// Schedule 사용(특정 시간마다 실행)
public class Gdj68BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Gdj68BootApplication.class, args);
	}

}
