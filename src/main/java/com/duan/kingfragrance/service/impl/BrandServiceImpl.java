package com.duan.kingfragrance.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.kingfragrance.model.Brand;
import com.duan.kingfragrance.repository.BrandRepository;
import com.duan.kingfragrance.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepo; 
	
	@Override
	public Boolean createBrand(Brand brand) {
		Optional<Brand> brandOptional = brandRepo.findByBrand(brand.getName());
		if (brandOptional.isPresent()) {
			return false;
		} else {
			brandRepo.save(brand);
			return true;
		}
	}

	@Override
	public List<Brand> getAllBrand() {
	return	brandRepo.findAll();	
	}

	@Override
	public Boolean deleteBrandById(String id) {
	Optional<Brand> brandOptional =	brandRepo.findById(id);
	if (brandOptional.isPresent()) {
		brandRepo.deleteById(id);
		return true;
	} else {
		return false;
		}
	}

	@Override
	public Boolean updateBrand(String id, Brand brand) {
		Optional<Brand> brandOptional = brandRepo.findById(id);
		if (brandOptional.isPresent()) {
			Brand brandUpdate = brandOptional.get();
			brandUpdate.setName(brand.getName());
			brandRepo.save(brandUpdate);
			return true;
		} else {
			return false;
			}
	}

}
