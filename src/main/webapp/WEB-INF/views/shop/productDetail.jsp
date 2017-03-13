<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 상세정보</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
	<h2>상품 상세정보</h2>
	<table>
		<tr>
			<td>
				<img src="${path}/images/${vo.productUrl}" width="300" height="300">
			</td>
			<td align="center">
				<table>
					<tr>
						<td>상품명</td>
						<td>${vo.productName}</td>
					</tr>
					<tr>
						<td>가격</td>
						<td><fmt:formatNumber value="${vo.productPrice}" pattern="###,###,###"/></td>
					</tr>
					<tr>
						<td>상품소개</td>
						<td>${vo.productDesc}</td>
					</tr>
					<tr>
						<td colspan="2">
							<a href="${path}/shop/product/list.do">상품목록</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>