<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
<%@ include file="../include/header.jsp" %>
<%-- <%@ include file="../include/sessionCheck.jsp" %> --%>
<script type="text/javascript" src="${path}/include/js/common.js"></script>
<script>
	
	$(document).ready(function(){
		// 파일 업로드 영역에서 기본효과를 제한
		$(".fileDrop").on("dragenter dragover", function(e){
			e.preventDefault(); // 기본효과 제한
		});
		$(".fileDrop").on("drop", function(e){
			e.preventDefault(); // 기본효과 제한
			var files = e.originalEvent.dataTransfer.files; // 드래그한 파일들
			//console.log(files);
			var file = files[0]; // 첫번째 첨부파일
			var formData = new FormData(); // 폼데이터 객체
			formData.append("file", file); // 첨부파일 추가
			$.ajax({
				url: "${path}/upload/uploadAjax",
				type: "post",
				data: formData,
				dataType: "text",
				processData: false, // processType: false - header가 아닌 body로 전달
				contentType: false,
				success: function(data){
					console.log(data);
					// 첨부 파일의 정보
					var fileInfo = getFileInfo(data);
					// 하이퍼링크
					var html = "<a href='"+fileInfo.getLink+"'>"+fileInfo.fileName+"</a><br>";
					// hidden 태그 추가
					html += "<input type='hidden' class='file' value='"+fileInfo.fullName+"'>";
					// div에 추가
					$("#uploadedList").append(html);
				}
			});
		});
		
		$("#btnSave").click(function(){
			//var title = document.form1.title.value; ==> name속성으로 처리할 경우
			//var content = document.form1.content.value;
			//var writer = document.form1.writer.value;
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
			
			// 첨부파일 이름을 form에 추가
			var that = $("#form1");
			var str = "";
			// 태그들.each(함수)
			// id가 uploadedList인 태그 내부에 있는 hidden태그들
			$("#uploadedList .file").each(function(i){
				str += "<input type='hidden' name='files["+i+"]' value='"+$(this).val()+"'>";
			});
			// form에 hidden태그들을 추가
			$("#form1").append(str);
			// 폼에 입력한 데이터를 서버로 전송
			document.form1.submit();
		});
		
	});
	
</script>
<style>
	/* 첨부파일을 드래그할 영역의 스타일 */
	.fileDrop {
		width: 600px;
		height: 70px;
		border: 2px dotted gray;
		background-color: gray;
	}

</style>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>게시글 작성</h2>
<form name="form1" id="form1" method="post" action="${path}/board/insert.do">
	<div>
		제목
		<input name="title" id="title" size="80" placeholder="제목을 입력해주세요">
	</div>
	<div>
		내용
		<textarea name="content" id="content" rows="4" cols="80" placeholder="내용을 입력해주세요"></textarea>
	</div>
	<div>
		첨부파일 등록
		<div class="fileDrop"></div>
		<!-- 첨부파일 목록 -->		
		<div id="uploadedList"></div>
	</div>
	<!-- <div>
		이름
		<input name="writer" id="writer" placeholder="이름을 입력해주세요">
	</div> -->
	<div style="width:650px; text-align: center;">
		<button type="button" id="btnSave">확인</button>
		<button type="reset">취소</button>
	</div>
</form>
</body>
</html>