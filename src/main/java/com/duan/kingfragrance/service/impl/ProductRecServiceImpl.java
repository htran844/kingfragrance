package com.duan.kingfragrance.service.impl;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.duan.kingfragrance.model.ProductRecommended;
import com.duan.kingfragrance.repository.ProductRecRepository;
import com.duan.kingfragrance.service.ProductRecService;

@Service
public class ProductRecServiceImpl implements ProductRecService {
	
	@Autowired
	private ProductRecRepository proRecRepo;
	@Override
	public ProductRecommended getProRec(String productId) {
		Optional<ProductRecommended> proRecOptional = proRecRepo.findByProductId(productId);
		if (proRecOptional.isPresent()) {
			ProductRecommended proRec = proRecOptional.get();
			return proRec;		
		} else {
			return null;
		}
		
	}

	@Override
	public Boolean createProRec(ProductRecommended proRec) {
		Optional<ProductRecommended> proRecOptional = proRecRepo.findByProductId(proRec.getProductId());
		if (proRecOptional.isPresent()) {
			return false;
		} else {
			proRecRepo.save(proRec);
			return true;
		}
	}

	@Override
	public Boolean deleteProRecByProductId(String productId) {
		Optional<ProductRecommended> proRecOptional = proRecRepo.findByProductId(productId);
		if (proRecOptional.isPresent()) {
			ProductRecommended proRec = proRecOptional.get();
			proRecRepo.deleteById(proRec.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean updateProRec(ProductRecommended proRec) {
		Optional<ProductRecommended> proRecOptional = proRecRepo.findByProductId(proRec.getProductId());
		if (proRecOptional.isPresent()) {
			ProductRecommended proRecUpdate = proRecOptional.get();
			proRecUpdate.setSeason(proRec.getSeason());
			proRecUpdate.setTime(proRec.getTime());
			proRecRepo.save(proRecUpdate);
			return true;
		} else {
			return false;
		}
	}

}
