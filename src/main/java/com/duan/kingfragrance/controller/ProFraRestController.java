package com.duan.kingfragrance.controller;

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

import com.duan.kingfragrance.model.ProductFragrance;

import com.duan.kingfragrance.service.ProductFraService;

@RestController
public class ProFraRestController {

	@Autowired
	private ProductFraService productFraService;
	
	@GetMapping("/admin/product-fra/{productId}")
	public ResponseEntity<?> getProductFra(@PathVariable String productId){
		ProductFragrance proFra = productFraService.getProFra(productId);
		if (proFra == null) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductFragrance>(proFra, HttpStatus.OK);
		}
	}
	@PostMapping("/admin/product-fra")
	public ResponseEntity<?> createProductFra(@RequestBody ProductFragrance proFra){
		Boolean result = productFraService.createProFra(proFra);
		if (!result) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductFragrance>(proFra, HttpStatus.OK);
		}
	}
	@DeleteMapping("/admin/product-fra/{productId}")
	public ResponseEntity<?> deleteProductFra(@PathVariable String productId){
		Boolean result = productFraService.deleteProFraByProductId(productId);
		if (result) {
			return new ResponseEntity<>("xoa thanh cong ProductFragrance có productId " +productId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	@PutMapping("/admin/product-fra")
	public ResponseEntity<?> updateProductFra(@RequestBody ProductFragrance proFra){
		Boolean result = productFraService.updateProFra(proFra);
		if (result) {
			return new ResponseEntity<>("update thanh cong ProductFragrance có productId " +proFra.getProductId(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
}
