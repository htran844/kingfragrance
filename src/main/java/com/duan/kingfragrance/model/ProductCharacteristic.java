package com.duan.kingfragrance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productCharacteristic")
public class ProductCharacteristic {

	@Id
	private String id;
	private String productId;
	private String release;
	private String gender;
	private String age;
	private String retention;
	public ProductCharacteristic(String id, String productId, String release, String gender, String age,
			String retention) {
		super();
		this.id = id;
		this.productId = productId;
		this.release = release;
		this.gender = gender;
		this.age = age;
		this.retention = retention;
	}
	public ProductCharacteristic() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getRetention() {
		return retention;
	}
	public void setRetention(String retention) {
		this.retention = retention;
	}
	
	
}
