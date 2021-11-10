package com.duan.kingfragrance.service;

import org.springframework.web.multipart.MultipartFile;

import com.duan.kingfragrance.model.Product;
import com.duan.kingfragrance.model.ProductFragrance;

public interface ProductService {
	
	public Product CreateProduct(Product product);
	
	public Product getOneProduct(String slug);
	
	public Boolean deleteProductBySlug(String slug);
	
	public Boolean updateProduct(Product product) ;
}
