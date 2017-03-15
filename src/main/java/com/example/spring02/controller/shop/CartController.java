package com.example.spring02.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dao.CartDAO;
import com.example.spring02.model.shop.dto.CartVO;
import com.example.spring02.service.shop.CartService;

@Controller
@RequestMapping("/shop/cart/*")
public class CartController {
	
	@Inject
	CartService cartService;
	
	// 1. 장바구니 추가
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute CartVO vo, HttpSession session){
		String userId = (String) session.getAttribute("userId");
		vo.setUserId(userId);
		// 장바구니에 기존 상품이 있는지 검사
		int count = cartService.countCart(vo.getProductId(), userId);
		if(count == 0){		
			// 없으면 insert
			cartService.insert(vo);
		} else {
			// 있으면 update
			cartService.updateCart(vo);
		}
		return "redirect:/shop/cart/list.do";
	}
	
	// 2. 장바구니 목록
	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav){
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId"); // session에 저장된 userId
		List<CartVO> list = cartService.listCart(userId); // 장바구니 정보 
		int sumMoney = cartService.sumMoney(userId); // 장바구니 전체 금액 호출
		// 장바구니 전체 긍액에 따라 배송비 구분
		// 배송료(10만원이상 => 무료, 미만 => 2500원)
		int fee = sumMoney >= 100000 ? 0 : 2500;
		map.put("list", list);				// 장바구니 정보를 map에 저장
		map.put("count", list.size());		// 장바구니 상품의 유무
		map.put("sumMoney", sumMoney);		// 장바구니 전체 금액
		map.put("fee", fee); 				// 배송금액
		map.put("allSum", sumMoney+fee);	// 주문 상품 전체 금액 
		mav.setViewName("shop/cartList");	// view(jsp)의 이름 저장
		mav.addObject("map", map);			// map 변수 저장
		return mav;
	}
	
	// 3. 장바구니 삭제
	@RequestMapping("delete.do")
	public String delete(@RequestParam int cartId){
		cartService.delete(cartId);
		return "redirect:/shop/cart/list.do";
	}
	
	// 4. 장바구니 수정
	@RequestMapping("update.do")
	public String update(@RequestParam int[] amount, @RequestParam int[] productId, HttpSession session) {
		// session의 id
		String userId = (String) session.getAttribute("userId");
		// 레코드의 갯수 만큰 반복문 실행
		for(int i=0; i<productId.length; i++){
			CartVO vo = new CartVO();
			vo.setUserId(userId);
			vo.setAmount(amount[i]);
			vo.setProductId(productId[i]);
			cartService.modifyCart(vo);
		}
		
		return "redirect:/shop/cart/list.do";
	}
}
