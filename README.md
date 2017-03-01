# spring02
스프링 게시판 만들기

***

### 10th Commit : Spring - 게시판 만들기 연습 (게시물 삭제를 delete대신 update처리)
##### 소스코드 변경사항
1. Model(DB연동 작업처리)
    * BoardVO에서 show추가
    * BoardMapper.xml 변경
		* 게시글 삭제 - delete쿼리를 update쿼리로 변경
		* 게시글 목록 - select쿼리 show 칼럼 추가
		* 게시글 입력 - insert쿼리 게시글 입력시 show칼럼에 기본값으로 'y'를 세팅
		* 게시글 상세 - select쿼리 변경 show 칼럼 추가

2. View(화면)
    * list.jsp(게시글 목록 화면)
    	* show컬럼이 y일 때(삭제상태가 아닐때)는 원래 게시물의 레코드를 출력
		* show컬럼이 n일 때(삭제상태일 때) "삭제된 게시물입니다"라는 문구를 출력
		* 삭제된 게시글에 댓글이 존재하면 댓글의 숫자를 출력하고, 댓글로 이동하기 위해 하이퍼링크로 연결
	* view.jsp(게시글 상세보기 화면)
		* show칼럼이 y이면(삭제상태가 아닐 때)는 원래 게시물의 레코드를 출력
		* show칼럼이 n이면(삭제상태일 때)는 '삭제된 게시물입니다'라는 문구를 출력

***
	
### 11th Commit : Spring - 게시판 만들기 연습(AOP 저장 로그 구현)
### AOP(Aspect Oriented Programming, 관점 지향 프로그래밍)

AOP는 Ioc/DI, 서비스 추상화와 더불어 스프링의 3대 기반기술 중에 하나이다. AOP는 스프링의 기술중에서 가장 이해하기 힘든 난해한 용어와 개념을 가진 기술로 악명이 높다. AOP를 바르게 이용하려면 OOP를 대체하려고 하는 것 처럼 보이는 AOP라는 이름 뒤에 감춰진, 그 필연적인 등장배경과 스프링이 도입한 이유, 그 적용을 통해 얻을 수 있는 장점이 무엇인지에 대한 충분한 이해가 필요하다.

##### AOP는 OOP(Object Oriented Programming, 객체지향프로그래밍)를 보완하는 확정적인 개념

> AOP란 OOP를 대신하는 새로운 개념이 아니라, OOP를 더욱 OOP답게 사용할 수 있도록 도와주는 개념이다.

OOP는 객체를 재사용함으로써 반복되는 코드의 양을 굉장히 많이 줄일수가 있었지만 객체의 재사용에도 불구하고 반복되는 코드를 없앨수는 없었다. 예를 들어 로그, 권한 체크, 인증, 예외 처리 등 필수적으로 해야하기 때문에 소스에서 반복될 수 밖에 없는 부분이 존재했다. AOP는 이러한 부분을 해결해주었다. 기능을 비지니스 로직과 공통 모듈로 구분한 후에 개발자의 코드 밖에서 필요한 시점에 비지니스 로직에 삽입하여 실행되도록 한다. 즉, OOP에서는 공통적인 기능을 각 객체의 횡단으로 입력했다면, AOP는 공통적인 기능을 종단간으로 삽입할 수 있도록 한 것이다.

![aop](http://cfile2.uf.tistory.com/image/2218DF4358B6958D0DE1B6)

기존의 OOP로직의 흐름은 계정, 게시판, 계좌이체를 처리할 때마다 똑같이 권한, 트랜잭션, 로깅을 처리해야하기 때문에 모든 로직에 똑같은 코드가 반복적으로 삽입될 수 밖에 없다. 즉, 권한, 로깅, 트랜잭션이라는 관심(Aspect)이 횡단으로 삽입되는 것이다.

하지만 AOP는 이러한 관심을 종단으로 삽입할 수 있도록 해준다. 기존의 OOP로직에서는 각 객체별로 처리했던 것들을 각 관점별로 외부에서 접근하는 것이 AOP의 핵심이다. 개발자는 계정, 게시판, 계좌이체와 같은 기능을 만들고, 공통적인 관심을 처리하는 모듈을 분리해서 개발한 뒤, 필요한 시점에 자동으로 소스코드가 삽입되도록 하는 것이다.

_ _ _

#### AOP 주요 용어와 개념
1. Aspect
	* 구현하고자 하는 횡단 관심사(로깅, 트랜잭션, 권한 등)의 기능을 의미한다.
	* 한개 이상의 포인트컷과 어드바이스의 조합으로 만들어진다.
2. Join Points
	* 관점(Aspect)를 삽입하여 어드바이스가 적용될 수 있는 위치를 의미한다.
	* method를 호출하는 시점, 예외가 발생하는 시점 등과 같이 특정 작업이 실행되는 시점을 의미하기도 한다.
3. Advice 
	* 관점(Aspect)의 구현체로 Join Points에서 실행되어야 하는 코드(실제로 AOP 기능을 구현한 객체)
	* Advice는 Join Point와 결합하여 동작하는 시점에 따라 5개로 구분된다.
    * Advice의 종류
        - Before Advice : Join Points 전에 실행되는 advice
		- After returning advice : Join Points에서 성공적으로 리턴 된 후 실행되는 advice
		- After throwing advice : 예외가 발생하였을 경우 실행되는 advice
		- After advice : Join Points에서 메서드의 실행결과에 상관없이 무조건 실행되는 advice, 자바의 finally와 비슷한 역할
		- Around advice : Join Points의 전 과정(전, 후)에 수행되는 advice(가장 광범위하게 사용)
4. Pointcuts
	* 어드바이스를 적용할 조인 포인트를 선별하는 과정이나 그 기능을 정의한 모듈을 의미한다. 패턴매칭을 이용하여 어떤 조인포인트를 사용할 것인지 결정한다.
5. Target
	* 어드바이스를 받을 대상, 즉 객체를 의미한다. 비지니스로직을 수행하는 클래스일수도 있지만, 프록시 객체(Object)가 될 수도 있다.

_ _ _


#### AOP의 설정 방법 (로깅 저장 예제)
1. `pom.xml` 에  라이브러리 추가
```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.8.9</version>
</dependency>
```
2. `servlet-context.xml`의 Namespace에 aop 추가
![servlet-context.xml](http://cfile2.uf.tistory.com/image/2113093F58B69E0D2F9267)
3. `servlet-context.xml`에 aop 태그 추가
```xml
<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
```
4. AOP 기능을 지원할 Advice 클래스 작성
```java
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
		if (type.indexOf("Controller") > -1) {
			name = "Controller:";
		} else if (type.indexOf("Service") > -1) {
			name = "ServiceImpl:";
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
```

_ _ _
#### 요약
> 중복되는 코드 제거, 효율적인 유지보수, 높은 생산성, 재활용성의 극대화, 변화 수용의 용이성이 AOP의 장점이자 가장 큰 특징

_ _ _

###### 사진 출처 및 참고
[스프링(Spring) 개발 - (16) AOP 설정하기 (부제: Controller에도 AOP 적용하기)][http://addio3305.tistory.com/86]
[Spring AOP의 이해][http://wiki.gurubee.net/pages/viewpage.action?pageId=26740833]
