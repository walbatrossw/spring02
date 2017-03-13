package com.example.spring02.controller.shop;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.service.shop.ProductService;
@Controller
@RequestMapping("shop/product/*")
public class ProductController {
	@Inject
	ProductService productService;
	
	@RequestMapping("/list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("/shop/productList");
		mav.addObject("list", productService.listProduct());
		return mav;
	}
	
	@RequestMapping("/detail/{productId}")
	public ModelAndView detail(@PathVariable("productId") int productId, ModelAndView mav){
		mav.setViewName("shop/productDetail");
		mav.addObject("vo", productService.detailProduct(productId));
		return mav;
	}
}
