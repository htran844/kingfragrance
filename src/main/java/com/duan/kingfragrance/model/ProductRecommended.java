package com.duan.kingfragrance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productRecommended")
public class ProductRecommended {

	@Id
	private String id;
	private String productId;
	private String season;
	private String time;
	public ProductRecommended(String id, String productId, String season, String time) {
		super();
		this.id = id;
		this.productId = productId;
		this.season = season;
		this.time = time;
	}
	public ProductRecommended() {
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
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
