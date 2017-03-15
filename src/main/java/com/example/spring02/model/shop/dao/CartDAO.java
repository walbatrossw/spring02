package com.example.spring02.model.shop.dao;

import java.util.List;

import com.example.spring02.model.shop.dto.CartVO;

public interface CartDAO {
	// 1. 장바구니추가
	public void insert(CartVO vo);
	// 2. 장바구니 리스트
	public List<CartVO> listCart(String userId);
	// 3. 장바구니 삭제
	public void delete(int cartId);
	// 4. 장바구니 수정
	public void modifyCart(CartVO vo);
	// 5. 장바구니 금액 합계
	public int sumMoney(String userId);
	// 6. 장바구니 상품확인
	public int countCart(int productId, String userId);
	// 7. 장바구니 상품수량 변경
	public void updateCart(CartVO vo);
}
