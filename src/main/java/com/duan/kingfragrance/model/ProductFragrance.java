package com.duan.kingfragrance.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productFragrance")
public class ProductFragrance {
	
	@Id
	private String id;
	private String productId;
	private String tone;
	private String first;
	private String midle;
	private String last;
	public ProductFragrance(String id, String productId, String tone, String first, String midle, String last) {
		super();
		this.id = id;
		this.productId = productId;
		this.tone = tone;
		this.first = first;
		this.midle = midle;
		this.last = last;
	}
	public ProductFragrance() {
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
	public String getTone() {
		return tone;
	}
	public void setTone(String tone) {
		this.tone = tone;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getMidle() {
		return midle;
	}
	public void setMidle(String midle) {
		this.midle = midle;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	
	
	
	
}
