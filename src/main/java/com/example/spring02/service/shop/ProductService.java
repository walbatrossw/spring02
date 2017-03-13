package com.example.spring02.service.shop;

import java.util.List;

import com.example.spring02.model.shop.dto.ProductVO;

public interface ProductService {
	// 01. 상품목록
	List<ProductVO> listProduct();
	// 02. 상품상세
	ProductVO detailProduct(int productId);
	// 03. 상품수정
	void updateProduct(ProductVO vo);
	// 04. 상품삭제
	void deleteProduct(int productId);
}
