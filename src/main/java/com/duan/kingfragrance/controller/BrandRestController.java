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

import org.springframework.web.bind.annotation.RestController;

import com.duan.kingfragrance.model.Brand;
import com.duan.kingfragrance.service.BrandService;

@RestController
public class BrandRestController {
	
	@Autowired
	private BrandService brandService;
	
	@GetMapping("/brand/crud")
	public ResponseEntity<?> getAllBrand(){
		List<Brand> brands = brandService.getAllBrand();
		
		return new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
	}
	@PostMapping("/brand/crud")
	public ResponseEntity<?> createBrand(@RequestBody Brand brand){
		Boolean result = brandService.createBrand(brand);
		if (result) {
			return new ResponseEntity<Brand>(brand, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Brand da ton tai", HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/brand/crud/{id}")
	public ResponseEntity<?> deleteBrand(@PathVariable("id") String id){
		Boolean result = brandService.deleteBrandById(id);
		if (result) {
			return new ResponseEntity<>("xoa thanh cong brand co id " +id, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Brand khong ton tai co id "+ id, HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/brand/crud/{id}")
	public ResponseEntity<?> updateBrand(@PathVariable("id") String id,@RequestBody Brand brand){
		Boolean result = brandService.updateBrand(id, brand);
		if (result) {
			return new ResponseEntity<>("update thanh cong brand co id " +id, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Brand khong ton tai co id "+ id, HttpStatus.NOT_FOUND);
		}
	}
}
