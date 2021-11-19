package com.duan.kingfragrance.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
public class Order {
	
	@Id
	private String id;
	private String userId;
	private String name;
	private String phone;
	private String email;
	private String note;
	private String address;
	private String status;
	private int totalCost;
	private Date createAt;
	private Date comfirmAt;
	private Date finishedAt;
	
	public Order(String id, String userId, String name, String phone, String email, String note, String address,
			String status, int totalCost, Date createAt, Date comfirmAt, Date finishedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.note = note;
		this.address = address;
		this.status = status;
		this.totalCost = totalCost;
		this.createAt = createAt;
		this.comfirmAt = comfirmAt;
		this.finishedAt = finishedAt;
	}

	public Order() {
		super();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", note=" + note + ", address=" + address + ", status=" + status + ", totalCost=" + totalCost
				+ ", createAt=" + createAt + ", comfirmAt=" + comfirmAt + ", finishedAt=" + finishedAt + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getComfirmAt() {
		return comfirmAt;
	}

	public void setComfirmAt(Date comfirmAt) {
		this.comfirmAt = comfirmAt;
	}

	public Date getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
	}
	
	
}
