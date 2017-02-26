<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
<%@ include file="../include/header.jsp" %>
<script>
	$(document).ready(function(){
		/* 게시글 관련 */
		// 1. 게시글 수정
		$("#btnUpdete").click(function(){
			/* var title = document.form1.title.value; ==> name속성으로 처리할 경우
			var content = document.form1.content.value;
			var writer = document.form1.writer.value; */
			var title = $("#title").val();
			var content = $("#content").val();
			//var writer = $("#writer").val();
			if(title == ""){
				alert("제목을 입력하세요");
				document.form1.title.focus();
				return;
			}
			if(content == ""){
				alert("내용을 입력하세요");
				document.form1.content.focus();
				return;
			}
			/* if(writer == ""){
				alert("이름을 입력하세요");
				document.form1.writer.focus();
				return;
			} */
			document.form1.action="${path}/board/update.do"
			// 폼에 입력한 데이터를 서버로 전송
			document.form1.submit();
		});
		
		// 2. 게시글 삭제
		$("#btnDelete").click(function(){
			if(confirm("삭제하시겠습니까?")){
				document.form1.action = "${path}/board/delete.do";
				document.form1.submit();
			}
		});
		
		// 3. 게시글 목록으로 이동 - 버튼 클릭시 상세보기화면에 있던 페이지, 검색옵션, 키워드 값을 가지로 목록으로 이동
		$("#btnList").click(function(){
			location.href="${path}/board/list.do?curPage=${curPage}&searchOption=${searchOption}&keyword=${keyword}";
		});
		
		/* 댓글 관련 */
		// 1. 댓글 입력
		$("#btnReply").click(function(){
			//reply(); // 폼데이터 형식으로 입력
			replyJson(); // json 형식으로 입력
		});
		
		// 2. 댓글 목록
		//listReply("1"); // 댓글 목록 불러오기
		//listReply2(); // json 리턴방식
		listReplyRest("1"); // rest방식
		
	});
	
	// 1_1. 댓글 입력 함수(폼 데이터 방식)
	function reply(){
		var replytext=$("#replytext").val();
		var bno="${dto.bno}"
		// 비밀댓글 체크여부
		var secretReply = "n";
		// 태그.is(":속성") 체크여부 true/false
		if( $("#secretReply").is(":checked") ){
			secretReply = "y";
		}
		// 비밀댓글 파라미터 추가
		var param="replytext="+replytext+"&bno="+bno+"&secretReply="+secretReply;
		$.ajax({				
			type: "post",
			url: "${path}/reply/insert.do",
			data: param,
			success: function(){
				alert("댓글이 등록되었습니다.");
				//listReply2();
				listReply("1");
			}
		});
	}
	
	// 1_2. 댓글 입력 함수(json방식)
	function replyJson(){
		var replytext=$("#replytext").val();
		var bno="${dto.bno}"
		// 비밀댓글 체크여부
		var secretReply = "n";
		// 태그.is(":속성") 체크여부 true/false
		if( $("#secretReply").is(":checked") ){
			secretReply = "y";
		}
		$.ajax({				
			type: "post",
			url: "${path}/reply/insertRest.do",
			headers: {
				"Content-Type" : "application/json"
			},
			dateType: "text",
			// param형식보다 간편
			data: JSON.stringify({
				bno : bno,
				replytext : replytext,
				secretReply : secretReply
			}),
			success: function(){
				alert("댓글이 등록되었습니다.");
				// 댓글 입력 완료후 댓글 목록 불러오기 함수 호출
				//listReply("1"); 	// 전통적인 Controller방식
				//listReply2(); 	// json리턴 방식
				listReplyRest("1"); // Rest 방식
			}
		});
	}
	// 2_1. 댓글 목록 - 전통적인 Controller방식
	function listReply(num){
		$.ajax({
			type: "get",
			url: "${path}/reply/list.do?bno=${dto.bno}&curPage="+num,
			success: function(result){
			// responseText가 result에 저장됨.
				$("#listReply").html(result);
			}
		});
	}
	// 2_2. 댓글 목록 - RestController를 이용 json형식으로 리턴
	function listReply2(){
		$.ajax({
			type: "get",
			//contentType: "application/json", ==> 생략가능(RestController이기때문에 가능)
			url: "${path}/reply/listJson.do?bno=${dto.bno}",
			success: function(result){
				console.log(result);
				var output = "<table>";
				for(var i in result){
					output += "<tr>";
					output += "<td>"+result[i].userName;
					output += "("+changeDate(result[i].regdate)+")<br>";
					output += result[i].replytext+"</td>";
					output += "<tr>";
				}
				output += "</table>";
				$("#listReply").html(output);
			}
		});
	}
	// 2_2. 댓글 목록 - 날짜 형식 변환 함수 작성
	function changeDate(date){
		date = new Date(parseInt(date));
		year = date.getFullYear();
		month = date.getMonth();
		day = date.getDate();
		hour = date.getHours();
		minute = date.getMinutes();
		second = date.getSeconds();
		strDate = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		return strDate;
	}
	
	// 2_3. 댓글 목록 - Rest방식
	function listReplyRest(num){
		$.ajax({
			type: "get",
			url: "${path}/reply/list/${dto.bno}/"+num,
			success: function(result){
			// responseText가 result에 저장됨.
				$("#listReply").html(result);
			}
		});
	}	
	
	// 댓글 수정화면 생성 함수
	function showReplyModify(rno){
		$.ajax({
			type: "get",
			url: "${path}/reply/detail/"+rno,
			success: function(result){
				$("#modifyReply").html(result);
				// 태그.css("속성", "값")
				$("#modifyReply").css("visibility", "visible");
			}
		})
	}
</script>
<style>
	#modifyReply {
		width: 600px;
		height: 130px;
		background-color: gray;
		padding: 10px;
		z-index: 10;
		visibility: hidden;
	}
</style>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
	<h2>게시글 보기</h2>

	<form name="form1" method="post">
		<div>		<!-- 원하는 날짜형식으로 출력하기 위해 fmt태그 사용 -->
			작성일자 : <fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd a HH:mm:ss"/>
					<!-- 날짜 형식 => yyyy 4자리연도, MM 월, dd 일, a 오전/오후, HH 24시간제, hh 12시간제, mm 분, ss 초 -->
		</div>
		<div>
			조회수 : ${dto.viewcnt}
		</div>
		<div>
			제목
			<input name="title" id="title" size="80" value="${dto.title}" placeholder="제목을 입력해주세요">
		</div>
		<div>
			내용
			<textarea name="content" id="content" rows="4" cols="80" placeholder="내용을 입력해주세요">${dto.content}</textarea>
		</div>
		<div>
			이름
			<%-- <input name="writer" id="writer" value="${dto.writer}" placeholder="이름을 입력해주세요"> --%>
			${dto.userName}
		</div>
		<div style="width:650px; text-align: center;">
			<!-- 게시물번호를 hidden으로 처리 -->
			<input type="hidden" name="bno" value="${dto.bno}">
		<!-- 본인이 쓴 게시물만 수정, 삭제가 가능하도록 처리 -->
		<c:if test="${sessionScope.userId == dto.writer}">
			<button type="button" id="btnUpdete">수정</button>
			<button type="button" id="btnDelete">삭제</button>
		</c:if>
			<!-- 상세보기 화면에서 게시글 목록화면으로 이동 -->
			<button type="button" id="btnList">목록</button>
		</div>
	</form>
	
	
	<div style="width:650px; text-align: center;">
		<br>
		<!-- 로그인 한 회원에게만 댓글 작성폼이 보이게 처리 -->
		<c:if test="${sessionScope.userId != null}">	
			<textarea rows="5" cols="80" id="replytext" placeholder="댓글을 작성해주세요"></textarea>
			<br>
			<!-- 비밀댓글 체크박스 -->
			<input type="checkbox" id="secretReply">비밀 댓글
			<button type="button" id="btnReply">댓글 작성</button>
		</c:if>
	</div>
	<!-- 댓글 목록 출력할 위치 -->
	<div id="listReply"></div>
</body>
</html>