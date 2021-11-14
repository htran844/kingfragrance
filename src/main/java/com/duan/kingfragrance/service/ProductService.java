package com.duan.kingfragrance.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import com.duan.kingfragrance.model.Product;
import com.duan.kingfragrance.model.ProductFragrance;
import com.duan.kingfragrance.model.ProductResult;

public interface ProductService {
	
	public Product CreateProduct(Product product);
	
	public Product getOneProduct(String slug);
	
	public Boolean deleteProductBySlug(String slug);
	
	public Boolean updateProduct(Product product) ;
	
	public List<Product> getAdminProduct(int page, String search, String gender);

	public List<ProductResult> getAllProductResult();
	
	public Page<Product> findPaginated(int pageNo, int pageSize);
}
