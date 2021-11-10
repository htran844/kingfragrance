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

import com.duan.kingfragrance.model.ProductRecommended;
import com.duan.kingfragrance.service.ProductRecService;

@RestController
public class ProRecRestController {
	
	@Autowired
	private ProductRecService proRecService;
	
	@GetMapping("/admin/product-rec")
	public ResponseEntity<?> getProductRec(@RequestParam("productId") String productId){
		ProductRecommended proRec = proRecService.getProRec(productId);
		if (proRec == null) {
			return new ResponseEntity<>("khong tim thay product recomened voi productId" +productId, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ProductRecommended>(proRec, HttpStatus.OK);
		}
	}
	@PostMapping("/admin/product-rec")
	public ResponseEntity<?> createProductRec(@RequestBody ProductRecommended proRec){
		Boolean result = proRecService.createProRec(proRec);
		if (!result) {
			return new ResponseEntity<>(" productId" +proRec.getProductId() + "da ton tai", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ProductRecommended>(proRec, HttpStatus.OK);
		}
	}
	@DeleteMapping("/admin/product-rec")
	public ResponseEntity<?> deleteProductRec(@RequestParam("productId") String productId){
		Boolean result = proRecService.deleteProRecByProductId(productId);
		if (result) {
			return new ResponseEntity<>("xoa thanh cong product recommended có productId " +productId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("khong tim thay product recommended có productId " +productId, HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/admin/product-rec")
	public ResponseEntity<?> updateProductRec(@RequestBody ProductRecommended proRec){
		Boolean result = proRecService.updateProRec(proRec);
		if (result) {
			return new ResponseEntity<>("update thanh cong product recommended có productId " +proRec.getProductId(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("khong tim thay product recommended có productId " +proRec.getProductId(), HttpStatus.NOT_FOUND);
		}
	}
}
