<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.userId == null}">
	<script>
		alert("로그인 하신 후에 사용해주세요");
		location.href="${path}/member/login.do"; 
	</script>
</c:if>