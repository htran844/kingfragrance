package com.duan.kingfragrance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duan.kingfragrance.model.ProductDetail;

import com.duan.kingfragrance.service.ProductDetailService;

@RestController
public class ProDetailRestController {
	
	@Autowired
	private ProductDetailService productDetailService;
	
	@GetMapping("/admin/product-detail")
	public ResponseEntity<?> getProductDetail(@RequestParam("productId") String productId){
		ProductDetail proDetail = productDetailService.getProDetail(productId);
		if (proDetail == null) {
			return new ResponseEntity<>("khong tim thay ProductDetail voi productId" +productId, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDetail>(proDetail, HttpStatus.OK);
		}
	}
	@PostMapping("/admin/product-detail")
	public ResponseEntity<?> createProductDetail(@RequestBody ProductDetail proDetail){
		Boolean result = productDetailService.createProDetail(proDetail);
		if (!result) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDetail>(proDetail, HttpStatus.OK);
		}
	}
	@DeleteMapping("/admin/product-detail")
	public ResponseEntity<?> deleteProductDetail(@RequestParam("productId") String productId){
		Boolean result = productDetailService.deleteProDetailByProductId(productId);
		if (result) {
			return new ResponseEntity<>("xoa thanh cong ProductDetail có productId " +productId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("khong tim thay ProductDetail có productId " +productId, HttpStatus.OK);
		}
	}
	@PutMapping("/admin/product-detail")
	public ResponseEntity<?> updateProDetail(@RequestBody ProductDetail productDetail){
		Boolean result = productDetailService.updateProDetail(productDetail);
		if (result) {
			return new ResponseEntity<>("update thanh cong ProductDetail có productId " +productDetail.getProductId(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("khong tim thay ProductDetail có productId " +productDetail.getProductId(), HttpStatus.OK);
		}
	}
}
