package com.duan.kingfragrance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/admin/product-detail/{productId}")
	public ResponseEntity<?> getAllProductDetail(@PathVariable String productId){
		List<ProductDetail> lst = productDetailService.getAllProductDetailById(productId);
		
		if (lst == null) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ProductDetail>>(lst, HttpStatus.OK);
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
	@DeleteMapping("/admin/product-detail/{productId}/{capacity}")
	public ResponseEntity<?> deleteProductDetail(@PathVariable String productId, @PathVariable int capacity){
		Boolean result = productDetailService.deleteProDetailByProductIdAndCapacity(productId, capacity);
		if (result) {
			return new ResponseEntity<>("xoa thanh cong ProductDetail có productId " +productId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	@PutMapping("/admin/product-detail")
	public ResponseEntity<?> updateProDetail(@RequestBody ProductDetail productDetail){
		Boolean result = productDetailService.updateProDetail(productDetail);
		if (result) {
			return new ResponseEntity<>("update thanh cong ProductDetail " +productDetail.getCapacity(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("khong tim thay ProductDetail có productId " +productDetail.getProductId(), HttpStatus.OK);
		}
	}
	@DeleteMapping("/admin/product-detail/{productId}")
	public ResponseEntity<?> deleteAllProductDetail(@PathVariable String productId){
		Boolean result = productDetailService.deleteAllProDetail(productId);
		if (result) {
			return new ResponseEntity<>("xoa thanh cong ProductDetail có productId " +productId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
}
