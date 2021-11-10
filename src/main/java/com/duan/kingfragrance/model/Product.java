package com.duan.kingfragrance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {
	
	@Id
	private String id;
	private String name;
	private String brand;
	private String slug;
	private String gender;
	private String image;
	private String description;
	private Boolean hot;
	public Product(String id, String name, String brand, String slug, String gender, String image, String description,
			Boolean hot) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.slug = slug;
		this.gender = gender;
		this.image = image;
		this.description = description;
		this.hot = hot;
	}
	public Product() {
		super();
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
	
	
}
