package com.duan.kingfragrance.service;

import com.duan.kingfragrance.model.ProductDetail;


public interface ProductDetailService {
	
	public ProductDetail getProDetail(String productId);
	
	public Boolean createProDetail(ProductDetail proDetail);

	public Boolean deleteProDetailByProductId(String productId);
	
	public Boolean updateProDetail(ProductDetail proDetail) ;
}
