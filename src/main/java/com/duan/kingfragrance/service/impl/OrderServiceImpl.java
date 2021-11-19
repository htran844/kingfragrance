package com.duan.kingfragrance.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.kingfragrance.model.Order;
import com.duan.kingfragrance.repository.OrderRepository;
import com.duan.kingfragrance.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Override
	public Order getOneOrderById(String id) {
		Optional<Order> optional = orderRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
		
	}

	@Override
	public List<Order> getAllOrder(String phone, String status) {
		List<Order> orders;
		if(phone.equalsIgnoreCase("null") && status.equalsIgnoreCase("null")) {
			orders = orderRepo.findAll();
		} else if(phone.equalsIgnoreCase("null")) {
			orders = orderRepo.findByStatus(status);
		} else if(status.equalsIgnoreCase("null")) {
			orders = orderRepo.findByPhone(phone);
		}else {
			orders = orderRepo.findAllAdminOrder(phone, status);
		}
		return orders;
		
	}

	@Override
	public Boolean createOrder(Order order) {
		order.setCreateAt(new Date(System.currentTimeMillis()));
		orderRepo.save(order);
		return true;
	}

	@Override
	public Boolean updateOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean changeStatusDelivery(String orderId) {
		Optional<Order> optional = orderRepo.findById(orderId);
		if (optional.isPresent()) {
			Order od = optional.get();
			od.setStatus("delivery");
			orderRepo.save(od);
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public Boolean changeStatusCancel(String orderId) {
		Optional<Order> optional = orderRepo.findById(orderId);
		if (optional.isPresent()) {
			Order od = optional.get();
			od.setStatus("cancelled");
			orderRepo.save(od);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean changeStatusRefund(String orderId) {
		Optional<Order> optional = orderRepo.findById(orderId);
		if (optional.isPresent()) {
			Order od = optional.get();
			od.setStatus("refund");
			orderRepo.save(od);
			return true;
		} else {
			return false;
		}
	}

}
