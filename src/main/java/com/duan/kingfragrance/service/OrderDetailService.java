package com.duan.kingfragrance.service;

import java.util.List;

import com.duan.kingfragrance.model.OrderDetail;

public interface OrderDetailService {
	
	Boolean createOrderDetail(OrderDetail orderDetail);
	
	List<OrderDetail> getListOdDetails(String orderId);
}
