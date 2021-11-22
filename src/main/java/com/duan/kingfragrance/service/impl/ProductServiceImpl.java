package com.duan.kingfragrance.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.duan.kingfragrance.model.Brand;
import com.duan.kingfragrance.model.Product;
import com.duan.kingfragrance.model.ProductDetail;
import com.duan.kingfragrance.model.ProductRecommended;
import com.duan.kingfragrance.model.ProductResult;
import com.duan.kingfragrance.repository.ProductDetailRepository;
import com.duan.kingfragrance.repository.ProductRepository;
import com.duan.kingfragrance.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ProductDetailRepository productDetailRepository;

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

	@Override
	public List<Product> getAdminProduct(int page, String search, String gender) {
		List<Product> listProduct;
		if (search.equalsIgnoreCase("null") && gender.equalsIgnoreCase("null")) {

			listProduct = productRepo.findAll();
		} else if (search.equalsIgnoreCase("null")) {
			listProduct = productRepo.findByGender(gender);
		} else if (gender.equalsIgnoreCase("null")) {
			listProduct = productRepo.findByName(search);
		} else {
			listProduct = productRepo.findAllAdminProduct(search, gender);
		}

//		listProduct	= productRepo.findAllAdminProduct(search, gender);
		return listProduct;
	}

	@Override
	public Page<Product> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		return productRepo.findAll(pageable);
	}

	@Override
	public ProductResult getOneProductResultBySlug(String Slug) {
		// TODO Auto-generated method stub
		Product product = getOneProduct(Slug);
		List<ProductDetail> lstProductDetail = productDetailRepository.findAllByProductId(product.getId());
		ProductResult productResult = new ProductResult(product, lstProductDetail);
		if (productResult == null) {
			return null;
		}
		return productResult;

	}

	@Override
	public List<ProductResult> getAllProductResult(List<Product> listProduct) {
		List<ProductResult> listProductResult = new ArrayList<ProductResult>();
		for (Product x : listProduct) {
			List<ProductDetail> lstProductDetailt = productDetailRepository.findAllByProductId(x.getId());
			Collections.sort(lstProductDetailt, Comparator.comparing(ProductDetail::getCapacity));// xep san pham co ml
																									// thap nhat len dau
			listProductResult.add(new ProductResult(x, lstProductDetailt));
		}
		if (listProductResult.size() > 0) {
			return listProductResult;
		}
		return null;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> list = productRepo.findAll();
		if (list.size()==0) {
			return null;
		}
		return list;
	}
	

	public Boolean deleteProductById(String id) {
		Optional<Product> optional = productRepo.findById(id);
		if (optional.isPresent()) {
			productRepo.deleteById(id);
			return true;
		} else {
			return false;
		}	
	}

	@Override

	public ProductResult getProductResultBySlugAndDetailId(String slug, String productDetailId) {
		Product product = getOneProduct(slug);
		System.out.println(product);
		ProductDetail productDetail = productDetailRepository.findByProductDetailId(productDetailId);
		List<ProductDetail> lstproductDetail = new ArrayList<>();
		lstproductDetail.add(productDetail);
		ProductResult productResult = new ProductResult(product, lstproductDetail);
		if (productResult!=null) {
			return productResult;
		}
		return null;
	}
	public Product getOneProductById(String id) {
		Optional<Product> optional = productRepo.findById(id);
		if (optional.isPresent()) {	
			return optional.get();
		} else {
			return null;
		}	

	}



}
