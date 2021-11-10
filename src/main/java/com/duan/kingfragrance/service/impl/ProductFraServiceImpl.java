package com.duan.kingfragrance.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.kingfragrance.model.ProductFragrance;
import com.duan.kingfragrance.model.ProductRecommended;
import com.duan.kingfragrance.repository.ProductFraRepository;
import com.duan.kingfragrance.service.ProductFraService;

@Service
public class ProductFraServiceImpl implements ProductFraService {

	@Autowired
	private ProductFraRepository productFraRepo;
	
	@Override
	public ProductFragrance getProFra(String productId) {
		Optional<ProductFragrance> proRecOptional = productFraRepo.findByProductId(productId);
		if (proRecOptional.isPresent()) {
			ProductFragrance proFra = proRecOptional.get();
			return proFra;		
		} else {
			return null;
		}
	}

	@Override
	public Boolean createProFra(ProductFragrance proFra) {
		Optional<ProductFragrance> proRecOptional = productFraRepo.findByProductId(proFra.getProductId());
		if (proRecOptional.isPresent()) {
			return false;
		} else {
			productFraRepo.save(proFra);
			return true;
		}
	}

	@Override
	public Boolean deleteProFraByProductId(String productId) {
		Optional<ProductFragrance> proRecOptional = productFraRepo.findByProductId(productId);
		if (proRecOptional.isPresent()) {
			ProductFragrance proFra = proRecOptional.get();
			productFraRepo.deleteById(proFra.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean updateProFra(ProductFragrance proFra) {
		Optional<ProductFragrance> proRecOptional = productFraRepo.findByProductId(proFra.getProductId());
		if (proRecOptional.isPresent()) {
			ProductFragrance proFraUpdate = proRecOptional.get();
			proFraUpdate.setTone(proFra.getTone());
			proFraUpdate.setFirst(proFra.getFirst());
			proFraUpdate.setMidle(proFra.getMidle());
			proFraUpdate.setLast(proFra.getLast());
			productFraRepo.save(proFraUpdate);
			return true;
		} else {
			return false;
		}
	}

}
