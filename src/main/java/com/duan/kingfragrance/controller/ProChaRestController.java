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

import com.duan.kingfragrance.model.ProductCharacteristic;

import com.duan.kingfragrance.service.ProductChaService;


@RestController
public class ProChaRestController {
	
	@Autowired
	private ProductChaService productChaService;
	
	@GetMapping("/admin/product-cha/{productId}")
	public ResponseEntity<?> getProductCha(@PathVariable String productId){
		ProductCharacteristic proCha = productChaService.getProCha(productId);
		if (proCha == null) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductCharacteristic>(proCha, HttpStatus.OK);
		}
	}
	@PostMapping("/admin/product-cha")
	public ResponseEntity<?> createProductCha(@RequestBody ProductCharacteristic proCha){
		Boolean result = productChaService.createProCha(proCha);
		if (!result) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductCharacteristic>(proCha, HttpStatus.OK);
		}
	}
	@DeleteMapping("/admin/product-cha/{productId}")
	public ResponseEntity<?> deleteProductCha(@PathVariable String productId){
		Boolean result = productChaService.deleteProChaByProductId(productId);
		if (result) {
			return new ResponseEntity<>("xoa thanh cong ProductCharacteristic có productId " +productId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	@PutMapping("/admin/product-cha")
	public ResponseEntity<?> updateProductCha(@RequestBody ProductCharacteristic proCha){
		Boolean result = productChaService.updateProCha(proCha);
		if (result) {
			return new ResponseEntity<>("update thanh cong ProductCharacteristic có productId " +proCha.getProductId(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("khong tim thay ProductCharacteristic có productId " +proCha.getProductId(), HttpStatus.OK);
		}
	}
}
