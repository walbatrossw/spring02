package com.example.spring02.service.shop;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dao.CartDAO;
import com.example.spring02.model.shop.dto.CartVO;

@Service
public class CartServiceImpl implements CartService {
	
	@Inject
	CartDAO cartDao;
	
	// 1. 장바구니 추가
	@Override
	public void insert(CartVO vo) {
		cartDao.insert(vo);
	}
	// 2. 장바구니 목록
	@Override
	public List<CartVO> listCart(String userId) {
		return cartDao.listCart(userId);
	}
	// 3. 장바구니 삭제
	@Override
	public void delete(int cartId) {
		cartDao.delete(cartId);
	}
	// 4. 장바구니 수정
	@Override
	public void modifyCart(CartVO vo) {
		cartDao.modifyCart(vo);
	}
	// 5. 장바구니 금액 합계
	@Override
	public int sumMoney(String userId) {
		return cartDao.sumMoney(userId);
	}
	// 6. 장바구니 상품확인
	@Override
	public int countCart(int productId, String userId) {
		return cartDao.countCart(productId, userId);
	}
	// 7. 장바구니 상품수량 변경
	@Override
	public void updateCart(CartVO vo) {
		cartDao.updateCart(vo);
	}

}
