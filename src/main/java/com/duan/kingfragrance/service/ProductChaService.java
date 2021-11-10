package com.duan.kingfragrance.service;

import com.duan.kingfragrance.model.ProductCharacteristic;


public interface ProductChaService {
	
	public ProductCharacteristic getProCha(String productId);
	
	public Boolean createProCha(ProductCharacteristic proCha);

	public Boolean deleteProChaByProductId(String productId);
	
	public Boolean updateProCha(ProductCharacteristic proCha) ;
}
