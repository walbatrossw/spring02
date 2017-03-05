package com.example.spring02.service.message;

import com.example.spring02.model.message.dto.MessageVO;

public interface MessageService {
	// 메시지 작성(DB저장, 포인트적립)
	public void addMessage(MessageVO vo);
	// 메시지 열람
	public MessageVO readMessage(String userid, int mid);
}
