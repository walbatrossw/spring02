package com.example.spring02.model.shop.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.shop.dto.ProductVO;


@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Inject
	SqlSession sqlSession;
	
	// 01. 상품목록
	@Override
	public List<ProductVO> listProduct() {
		return sqlSession.selectList("product.listProduct");
	}
	// 02. 상품상세
	@Override
	public ProductVO detailProduct(int productId) {
		return sqlSession.selectOne("product.detailProduct", productId);
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
