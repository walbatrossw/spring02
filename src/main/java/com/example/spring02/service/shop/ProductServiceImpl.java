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
		productDao.updateProduct(vo);
	}
	// 04. 상품삭제
	@Override
	public void deleteProduct(int productId) {
		productDao.deleteProduct(productId);
	}
	// 05. 상품추가
	@Override
	public void insertProduct(ProductVO vo) {
		productDao.insertProduct(vo);	
	}
	// 06. 상품이미지 삭제를 위한 이미지파일 정보
	@Override
	public String fileInfo(int productId) {
		return productDao.fileInfo(productId);
	}

}
