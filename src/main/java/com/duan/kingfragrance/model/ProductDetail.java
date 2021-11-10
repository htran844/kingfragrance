package com.duan.kingfragrance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productDetail")
public class ProductDetail {
	
	@Id
	private String id;
	private String productId;
	private int capacity;
	private int cost;
	private int quantity;
	public ProductDetail(String id, String productId, int capacity, int cost, int quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.capacity = capacity;
		this.cost = cost;
		this.quantity = quantity;
	}
	public ProductDetail() {
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
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
