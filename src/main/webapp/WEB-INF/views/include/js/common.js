	// 이미지 파일 여부 판단
	function checkImageType(fileName){
		var pattern = /jpg|gif|png|jpeg/i;
		return fileName.match(pattern);
	}
	
	// 업로드 파일 정보
	function getFileInfo(fullName){
		var fileName, imgsrc, getLink, fileLink;
		// 이미지 파일일 경우
		if(checkImageType(fullName)){
			// 이미지 파열 경로(썸네일)
			imgsrc = "/spring02/upload/displayFile?fileName="+fullName;
			console.log(imgsrc);
			// 업로드 파일명
			fileLink = fullName.substr(14);
			console.log(fileLink);
			// 날짜별 디렉토리 추출
			var front = fullName.substr(0, 12);
			console.log(front);
			// s_를 제거한 업로드이미지파일명
			var end = fullName.substr(14);
			console.log(end);
			// 원본이미지 파일 디렉토리
			getLink = "/spring02/upload/displayFile?fileName="+front+end;
			console.log(getLink);
		// 이미지 파일이 아닐경우
		} else {
			// UUID를 제외한 원본파일명
			fileLink = fullName.substr(12);
			console.log(fileLink);
			// 일반파일디렉토리 
			getLink = "/spring02/upload/displayFile?fileName="+fullName;
			console.log(getLink);
		}
		// 목록에 출력할 원본파일명
		fileName = fileLink.substr(fileLink.indexOf("_")+1);
		console.log(fileName);
		// { 변수:값 } json 객체 리턴
		return {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
	}
