package com.duan.kingfragrance.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.kingfragrance.model.ProductCharacteristic;

import com.duan.kingfragrance.repository.ProductChaRepository;

import com.duan.kingfragrance.service.ProductChaService;

@Service
public class ProductChaServiceImpl implements ProductChaService {

	@Autowired
	private ProductChaRepository productChaRepo;
	
	@Override
	public ProductCharacteristic getProCha(String productId) {
		Optional<ProductCharacteristic> proRecOptional = productChaRepo.findByProductId(productId);
		if (proRecOptional.isPresent()) {
			ProductCharacteristic proCha = proRecOptional.get();
			return proCha;		
		} else {
			return null;
		}
	}

	@Override
	public Boolean createProCha(ProductCharacteristic proCha) {
		Optional<ProductCharacteristic> proRecOptional = productChaRepo.findByProductId(proCha.getProductId());
		if (proRecOptional.isPresent()) {
			return false;
		} else {
			productChaRepo.save(proCha);
			
			return true;
		}
	}

	@Override
	public Boolean deleteProChaByProductId(String productId) {
		Optional<ProductCharacteristic> proRecOptional = productChaRepo.findByProductId(productId);
		if (proRecOptional.isPresent()) {
			ProductCharacteristic proFra = proRecOptional.get();
			productChaRepo.deleteById(proFra.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean updateProCha(ProductCharacteristic proCha) {
		Optional<ProductCharacteristic> proRecOptional = productChaRepo.findByProductId(proCha.getProductId());
		if (proRecOptional.isPresent()) {
			ProductCharacteristic proFraUpdate = proRecOptional.get();
			proFraUpdate.setRelease(proCha.getRelease());
			proFraUpdate.setGender(proCha.getGender());
			proFraUpdate.setAge(proCha.getAge());
			proFraUpdate.setRetention(proCha.getRetention());
			productChaRepo.save(proFraUpdate);
			return true;
		} else {
			return false;
		}
	}

}
