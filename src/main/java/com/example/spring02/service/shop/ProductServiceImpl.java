package com.example.spring02.service.shop;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dao.ProductDAO;
import com.example.spring02.model.shop.dto.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Inject
	ProductDAO productDao;
	
	// 01. 상품목록
	@Override
	public List<ProductVO> listProduct() {
		return productDao.listProduct();
	}
	// 02. 상품상세
	@Override
	public ProductVO detailProduct(int productId) {
		return productDao.detailProduct(productId);
	}
	// 03. 상품수정
	@Override
	public void updateProduct(ProductVO vo) {
		// TODO Auto-generated method stub

	}
	// 04. 상품삭제
	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub

	}

}
