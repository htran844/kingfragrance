package com.duan.kingfragrance.service;

import java.util.List;

import com.duan.kingfragrance.model.Brand;

public interface BrandService {
	
	public Boolean createBrand(Brand brand);
	
	public List<Brand> getAllBrand();
	
	public Boolean deleteBrandById(String id);
	
	public Boolean updateBrand(String id, Brand brand) ;
}
