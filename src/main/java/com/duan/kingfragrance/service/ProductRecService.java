package com.duan.kingfragrance.service;


import com.duan.kingfragrance.model.ProductRecommended;


public interface ProductRecService {
	
	public ProductRecommended getProRec(String productId);
	
	public Boolean createProRec(ProductRecommended proRec);

	public Boolean deleteProRecByProductId(String productId);
	
	public Boolean updateProRec(ProductRecommended proRec) ;
}
