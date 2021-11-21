package com.duan.kingfragrance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orderDetail")
public class OrderDetail {

	@Id
	private String id;
	private String orderId;
	private String productId;
	private String capacity;
	private String cost;
	private String quantity;
	private String total;
	public OrderDetail(String id, String orderId, String productId, String capacity, String cost, String quantity,
			String total) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.capacity = capacity;
		this.cost = cost;
		this.quantity = quantity;
		this.total = total;
	}
	public OrderDetail() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	
}
