package com.duan.kingfragrance.model;

import java.util.List;

public class ProductResult {
	private Product product;
	private List<ProductDetail> productDetails;
	public ProductResult() {
		
	}
	public ProductResult(Product product, List<ProductDetail> productDetails) {
		super();
		this.product = product;
		this.productDetails = productDetails;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<ProductDetail> getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(List<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}
	
}
