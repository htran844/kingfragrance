package com.duan.kingfragrance.service.impl;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.kingfragrance.model.Brand;
import com.duan.kingfragrance.model.Product;
import com.duan.kingfragrance.model.ProductRecommended;
import com.duan.kingfragrance.repository.ProductRepository;
import com.duan.kingfragrance.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public Product CreateProduct(Product product) {
		Optional<Product> Optional = productRepo.findBySlug(product.getSlug());
		if (Optional.isPresent()) {
			return null;
		} else {
			Product result = productRepo.save(product);
			return result;
		}
	}

	@Override
	public Product getOneProduct(String slug) {
		Optional<Product> Optional = productRepo.findBySlug(slug);
		if (Optional.isPresent()) {
			Product product = Optional.get();
			return product;		
		} else {
			return null;
		}
		
	}

	@Override
	public Boolean deleteProductBySlug(String slug) {
		Optional<Product> Optional = productRepo.findBySlug(slug);
		if (Optional.isPresent()) {
			Product product = Optional.get();
			productRepo.deleteById(product.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean updateProduct(Product product) {
		Optional<Product> Optional = productRepo.findBySlug(product.getSlug());
		if (Optional.isPresent()) {
			Product productUpdate = Optional.get();
			productUpdate.setName(product.getName());
			productUpdate.setBrand(product.getBrand());
			productUpdate.setGender(product.getGender());
			productUpdate.setImage(product.getImage());
			productUpdate.setDescription(product.getDescription());
			productUpdate.setHot(product.getHot());
			productRepo.save(productUpdate);
			return true;
		} else {
			return false;
		}
	}

}
