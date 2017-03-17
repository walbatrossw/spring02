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
		sqlSession.update("product.updateProduct", vo);

	}
	// 04. 상품삭제
	@Override
	public void deleteProduct(int productId) {
		sqlSession.delete("product.deleteProduct", productId);
	}
	// 05. 상품 추가
	@Override
	public void insertProduct(ProductVO vo) {
		sqlSession.insert("product.insertProduct", vo);
	}
	// 06. 상품이미지 삭제를 위한 이미지파일 정보
	@Override
	public String fileInfo(int productId) {
		return sqlSession.selectOne("product.fileInfo",productId);
	}

}
