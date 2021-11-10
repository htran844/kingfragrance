package com.duan.kingfragrance.service;

import com.duan.kingfragrance.model.ProductFragrance;


public interface ProductFraService {
	
	public ProductFragrance getProFra(String productId);
	
	public Boolean createProFra(ProductFragrance proFra);

	public Boolean deleteProFraByProductId(String productId);
	
	public Boolean updateProFra(ProductFragrance proFra) ;
}
