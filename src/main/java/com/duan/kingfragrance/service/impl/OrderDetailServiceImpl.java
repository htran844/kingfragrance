package com.duan.kingfragrance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.kingfragrance.model.OrderDetail;
import com.duan.kingfragrance.repository.OrderDetailRepository;
import com.duan.kingfragrance.service.OrderDetailService;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepo;
	@Override
	public Boolean createOrderDetail(OrderDetail orderDetail) {
		orderDetailRepo.save(orderDetail);
		return true;
	}
	@Override
	public List<OrderDetail> getListOdDetails(String orderId) {
		List<OrderDetail> lst = orderDetailRepo.getlstOdDetails(orderId);
		if (lst.size()>0) {
			return lst;
		} else {
			return null;
		}
		
	}

}
