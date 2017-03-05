package com.example.spring02.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// Advice : 공통업무를 지원하는 클래스
@Component // 기타 bean
@Aspect // AOP bean
public class MessageAdvice {
	private static final Logger logger = LoggerFactory.getLogger(MessageAdvice.class);
	
	// @Before(대상:호출전), @After(대상:호출후), @Around(대상:호출전 후)
	@Before("execution(* com.example.spring02.service.message.MessageService*.*(..))")
	public void startLog(JoinPoint jp){
		// 핵심업무의 클래스,매서드, 매개변수 로깅 
		logger.info("핵심업무 코드정보  : "+jp.getSignature());
		logger.info("메서드 : "+jp.getSignature().getName());
		logger.info("매개변수:"+Arrays.toString(jp.getArgs()));
	}
	
	// method 실행 시간 확인, @Around : 핵심업무 전후에 자동호출, ProceedingJoinPoint
	@Around("execution(* com.example.spring02.service.message.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		// 핵심업무 실행 전
		long start = System.currentTimeMillis(); 
		// 핵심업무 실행
		Object result = pjp.proceed(); 
		// 핵심업무 실행 후
		long end = System.currentTimeMillis(); 
		// 핵심업무 실행시간 연산
		logger.info(pjp.getSignature().getName()+"메서드 실행시간:"+(end-start));
		logger.info("==========================================");
		return result;
	}
}
