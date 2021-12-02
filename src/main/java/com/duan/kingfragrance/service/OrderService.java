package com.duan.kingfragrance.service;

import java.util.List;

import com.duan.kingfragrance.model.Order;

public interface OrderService {

	public Order getOneOrderById(String id);
	
	public List<Order> getAllOrder(String phone, String status);
	
	public Order createOrder(Order order);
	
	public Boolean updateOrder(Order order);
	
	public Boolean changeStatusDelivery(String orderId);
	
	public Boolean changeStatusCancel(String orderId);
	
	public Boolean changeStatusRefund(String orderId);
	
	public Boolean changeStatusSuccess(String orderId);
}
