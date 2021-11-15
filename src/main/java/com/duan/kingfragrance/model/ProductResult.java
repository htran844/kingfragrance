package com.duan.kingfragrance.model;

import java.util.List;

public class ProductResult {
	private String id;
	private String name;
	private String brand;
	private String slug;
	private String gender;
	private String image;
	private String description;
	private Boolean hot;
	private List<ProductDetail> productDetails;
	public ProductResult() {
		
	}
	public ProductResult(String id, String name, String brand, String slug, String gender, String image,
			String description, Boolean hot, List<ProductDetail> productDetails) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.slug = slug;
		this.gender = gender;
		this.image = image;
		this.description = description;
		this.hot = hot;
		this.productDetails = productDetails;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getHot() {
		return hot;
	}
	public void setHot(Boolean hot) {
		this.hot = hot;
	}
	public List<ProductDetail> getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(List<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}
	@Override
	public String toString() {
		return "ProductResult [id=" + id + ", name=" + name + ", brand=" + brand + ", slug=" + slug + ", gender="
				+ gender + ", image=" + image + ", description=" + description + ", hot=" + hot + ", productDetails="
				+ productDetails + "]";
	}
	
}
