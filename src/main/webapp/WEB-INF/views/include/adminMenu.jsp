<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl 코어 태그 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="${path}/shop/product/list.do">상품목록</a> |
<a href="${path}/shop/product/write.do">상품등록</a> |
<c:choose>
	<c:when test="${sessionScope.adminId == null}">
		<a href="${path}/admin/login.do">관리자 로그인</a>
	</c:when>
	<c:otherwise>
		${sessionScope.adminName}님이 로그인중입니다.
		<a href="${path}/admin/logout.do">로그아웃</a>
	</c:otherwise>
</c:choose>
	
<hr>