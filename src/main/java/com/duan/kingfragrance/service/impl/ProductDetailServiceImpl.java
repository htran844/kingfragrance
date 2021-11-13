package com.duan.kingfragrance.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.duan.kingfragrance.model.ProductDetail;

import com.duan.kingfragrance.repository.ProductDetailRepository;
import com.duan.kingfragrance.service.ProductDetailService;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

	@Autowired
	private ProductDetailRepository productDetailRepo;
	
	@Override
	public ProductDetail getProDetail(String productId) {
		Optional<ProductDetail> proDetailOptional = productDetailRepo.findByProductId(productId);
		if (proDetailOptional.isPresent()) {
			ProductDetail proDetail = proDetailOptional.get();
			return proDetail;		
		} else {
			return null;
		}
	}

	@Override
	public Boolean createProDetail(ProductDetail proDetail) {
		Optional<ProductDetail> proDetailOptional = productDetailRepo.findByCapacityAndProductId(proDetail.getProductId(),proDetail.getCapacity());
		if (proDetailOptional.isPresent()) {
			return false;
		} else {
			productDetailRepo.save(proDetail);
			return true;
		}
	}

	@Override
	public Boolean deleteProDetailByProductId(String productId) {
		Optional<ProductDetail> proDetailOptional = productDetailRepo.findByProductId(productId);
		if (proDetailOptional.isPresent()) {
			ProductDetail proDetail = proDetailOptional.get();
			productDetailRepo.deleteById(proDetail.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean updateProDetail(ProductDetail proDetail) {
		Optional<ProductDetail> proDetailOptional = productDetailRepo.findByProductId(proDetail.getProductId());
		if (proDetailOptional.isPresent()) {
			ProductDetail proDetailUpdate = proDetailOptional.get();
			proDetailUpdate.setCapacity(proDetail.getCapacity());
			proDetailUpdate.setCost(proDetail.getCost());
			proDetailUpdate.setQuantity(proDetail.getQuantity());
			productDetailRepo.save(proDetail);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<ProductDetail> getAllProductDetailById(String productId) {
		List<ProductDetail> lst = productDetailRepo.findAllByProductId(productId);
		if (lst.size()>0) {
			return lst;
		} else {
			return null;
		}
		
	}

}
