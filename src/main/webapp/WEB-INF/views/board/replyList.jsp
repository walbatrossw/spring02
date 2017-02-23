<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
	<table style="width:700px">
		<!-- 댓글 목록 -->
		<c:forEach var="row" items="${list}">
		<tr>	
			<td>
				${row.userName}(<fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>)
				<br>
				${row.replytext}
				<hr>	
			</td>
		</tr>
		</c:forEach>
		<!-- 페이지 나누기 -->
		<tr>
			<td>
				<c:if test="${replyPager.curBlock > 1}">
					<a href="javascript:listReply('1')">[처음]</a>
				</c:if>
				<c:if test="${replyPager.curBlock > 1}">
					<a href="javascript:listReply('${replyPager.prevPage}')">[이전]</a>
				</c:if>
				<c:forEach var="num" begin="${replyPager.blockBegin}" end="${replyPager.blockEnd}">
					<c:choose>
						<c:when test="${num == replyPager.curPage}">
							${num}&nbsp;
						</c:when>
						<c:otherwise>
							<a href="javascript:listReply('${num}')">${num}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${replyPager.curBlock <= replyPager.totBlock}">
					<a href="javascript:listReply('${replyPager.nextPage}')">[다음]</a>
				</c:if>
				<c:if test="${replyPager.curBlock <= replyPager.totBlock}">
					<a href="javascript:listReply('${replyPager.totPage}')">[끝]</a>
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>