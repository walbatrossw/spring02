<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<style>
	.fileDrop {
		width:600px;
		height: 200px;
		border: 1px dotted blue;
	}
	
	small {
		margin-left: 3px;
		font-weight: bold;
		color: gray;
	}
</style>
<script>
	$(document).ready(function() {
		
		// 1. 드래그 영역 기본 효과(바로보기)를 제한
		$(".fileDrop").on("dragenter dragover", function(event) {
			event.preventDefault(); // 기본효과를 제한
		});
		
		// 2. 파일 업로드
		// event : jQuery 이벤트, originalEvent : javascript 이벤트
		$(".fileDrop").on("drop", function(event) {
			event.preventDefault(); // 기본효과를 제한
			// 드래그된 파일의 정보
			var files = event.originalEvent.dataTransfer.files;
			// 첫번째 파일
			var file = files[0];
			// 콘솔에서 파일정보 확인
			console.log(file);
			// ajax로 전달할 폼 객체
			var formData = new FormData();
			// 폼 객체에 파일추가, append("변수명", 값)
			formData.append("file", file);
			
			$.ajax({
				type: "post",
				url: "${path}/upload/uploadAjax",
				data: formData,
				dataType: "text",
				// processData: true=> get방식, false => post방식
				processData: false,
				// contentType: true => application/x-www-form-urlencoded, 기본옵션
				//				false => multipart/form-data, 멀티파트
				contentType: false,
				success: function(data) {
					//alert(data);
					console.log(data);
					var str = "";
					// 이미지파일이면 썸네일 이미지 출력
					if(checkImageType(data)){ 
						str = "<div><a href='${path}/upload/displayFile?fileName="+getImageLink(data)+"'>";
						str += "<img src='${path}/upload/displayFile?fileName="+data+"'></a>";
					// 이미지 파일이 아니면 다운로드
					} else { 
						str = "<div><a href='${path}/upload/displayFile?fileName="+data+"'>"+getOriginalName(data)+"</a>";	
					}
						str += "<span data-src="+data+">[삭제]</span></div>";
					$(".uploadedList").append(str);
				}
			});
		});

		// 2. 파일 삭제
		// 태그.on("이벤트", "자손태그", "이벤트핸들러")
		$(".uploadedList").on("click", "span", function(event){
			alert("이미지 삭제")
			var that = $(this); // 여기서 this는 클릭한 span태그
			$.ajax({
				url: "${path}/upload/deleteFile",
				type: "post",
				// data: "fileName="+$(this).attr("date-src") = {fileName:$(this).attr("data-src")}
				// 태그.attr("속성")
				data: {fileName:$(this).attr("data-src")}, // json방식
				dataType: "text",
				success: function(result){
					if( result == "deleted" ){
						// 클릭한 span태그가 속한 div를 제거
						that.parent("div").remove();
					}
				}
			});
		});
	
	});
	
	// 원본파일이름
	function getOriginalName(fileName) {
		// 이미지 파일이면
		if(checkImageType(fileName)) {
			return; // 함수종료
		}
		// uuid를 제외한 원래 파일 이름을 리턴
		var idx = fileName.indexOf("_")+1;
		return fileName.substr(idx);
	}
	
	// 이미지파일 링크
	function getImageLink(fileName) {
		// 이미지파일이 아니면
		if(!checkImageType(fileName)) { 
			return; // 함수 종료 
		}
		// 이미지 파일이면
		var front = fileName.substr(0, 12); // 연원일경로 추출
		var end = fileName.substr(14); // s_ 제거
		console.log(front);
		console.log(end);
		return front+end;
	}
	
	// 이미지파일 형식 체크
	function checkImageType(fileName) {
		// i : ignore case(대소문자 무관)
		var pattern = /jpg|gif|png|jpeg/i; // 정규표현식
		return fileName.match(pattern); // 규칙이 맞으면 true
	}
	
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
	<h2>AJAX File Upload</h2>
	<!-- 파일을 업로드할 영역 -->
	<div class="fileDrop"></div>
	<!-- 업로드된 파일 목록 -->
	<div class="uploadedList"></div>
</body>
</html>