package com.duan.kingfragrance.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productFragrance")
public class ProductFragrance {
	
	@Id
	private String id;
	private String productId;
	private List<String> tone;
	private List<String> first;
	private List<String> midle;
	private List<String> last;
	public ProductFragrance(String id, String productId, List<String> tone, List<String> first, List<String> midle,
			List<String> last) {
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
	public List<String> getTone() {
		return tone;
	}
	public void setTone(List<String> tone) {
		this.tone = tone;
	}
	public List<String> getFirst() {
		return first;
	}
	public void setFirst(List<String> first) {
		this.first = first;
	}
	public List<String> getMidle() {
		return midle;
	}
	public void setMidle(List<String> midle) {
		this.midle = midle;
	}
	public List<String> getLast() {
		return last;
	}
	public void setLast(List<String> last) {
		this.last = last;
	}
	
	
}
