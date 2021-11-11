package com.duan.kingfragrance.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.duan.kingfragrance.model.Product;
import com.duan.kingfragrance.model.ProductCharacteristic;
import com.duan.kingfragrance.repository.ProductRepository;
import com.duan.kingfragrance.service.ProductService;

import ch.qos.logback.core.joran.conditional.IfAction;

@RestController
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepo;
	@PostMapping("admin/product-up")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("slug") String slug){
		Optional<Product> optional = productRepo.findBySlug(slug);
		if(optional.isPresent()) {
			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
					"cloud_name", "hoang844",
					"api_key", "217959259192693",
					"api_secret", "RSCRmsau04ynTetZx3L3jYGNbkY",
					"secure", true));
			String link ="" ;
			try {
				Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
				link = String.valueOf(uploadResult.get("url")) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Product product = optional.get();
			product.setImage(link);
			productRepo.save(product);
			return new ResponseEntity<String>(link, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("admin/product-main")
	public ResponseEntity<?> createProduct(@RequestBody Product product){
		Product result = productService.CreateProduct(product);
		if (result!=null) {
			return new ResponseEntity<Product>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
//		return new ResponseEntity<>("hihi", HttpStatus.OK);
	}
	@GetMapping("/admin/product-main")
	public ResponseEntity<?> getProduct(@RequestParam("slug") String slug){
		Product product = productService.getOneProduct(slug);
		if (product == null) {
			return new ResponseEntity<>("khong tim thay Product voi slug" +slug, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
	}
	@DeleteMapping("/admin/product-main")
	public ResponseEntity<?> deleteProduct(@RequestParam("slug") String slug){
		Boolean result = productService.deleteProductBySlug(slug);
		if (result) {
			return new ResponseEntity<>("xoa thanh cong Product có slug " +slug, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("khong tim thay Product có slug " +slug, HttpStatus.OK);
		}
	}
	@PutMapping("/admin/product-main")
	public ResponseEntity<?> updateProductCha(@RequestBody Product product){
		Boolean result = productService.updateProduct(product);
		if (result) {
			return new ResponseEntity<>("update thanh cong Product có slug " +product.getSlug(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("khong tim thay Product có slug " +product.getSlug(), HttpStatus.OK);
		}
	}
	
}
