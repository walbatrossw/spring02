package com.example.spring02.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // 스프링에서 관리하는 bean
@Aspect // AOP bean
public class LogAdvice {
	// private : 외부에서 로그를 가로채지 못하도록 하기 위해
	// static final : 로그 내용이 바뀌지 않으므로
	// 로깅툴을 사용하는 이유 : sysout명령어는 IO리소스를 많이 사용하여 시스템이 느려질 수 있다, 로그를 파일로 저장하여 분석할 필요가 있다.
	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	
	
	// PointCut - 실행 시점
	// @Before, @After, @Around
	// 컨트롤러, 서비스, DAO의 모든 method를 실행 전후에 logPrint method가 자동으로 실행된다.
	// .. : 하위의 모든 디렉토리를 의미
	// *(..) : * - 하위의 모든 메서드, (..) - 모든 매개변수
	@Around("execution(* com.example.spring02.controller..*Controller.*(..))"
			+ " or execution(* com.example.spring02.service..*Impl.*(..))"
			+ " or execution(* com.example.spring02.model..dao.*Impl.*(..))")
	public Object logPrinnt(ProceedingJoinPoint joinPoint) throws Throwable{
		// 실행 시간 체크 : 시작시간
		long start = System.currentTimeMillis();
		// 핵심로직으로 이동
		Object result = joinPoint.proceed();
		// 클래스 이름
		String type = joinPoint.getSignature().getDeclaringTypeName();
		String name = "";
		// Controller라면
		if (type.indexOf("Controller") > -1) {
			name = "Controller:";
		// Service라면
		} else if (type.indexOf("Service") > -1) {
			name = "ServiceImpl:";
		// DAO라면
		} else if (type.indexOf("DAO") > -1) {
			name = "DAO:";
		}
		// 메서드 이름
		logger.info(name+type+"."+joinPoint.getSignature().getName()+"()");
		// 파라미터 이름
		logger.info(Arrays.toString(joinPoint.getArgs()));
		// 실행 시간 체크 : 종료시간
		long end = System.currentTimeMillis();
		// 실행 시간 체크 : 연산
		long time = end-start;
		logger.info("실행 시간:"+time);
		return result;
	}
}
