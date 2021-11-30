package com.duan.kingfragrance.service;

import java.util.List;

import com.duan.kingfragrance.model.OrderDetail;

public interface OrderDetailService {
	
	Boolean createOrderDetail(OrderDetail orderDetail);
	
	Boolean updateOrderDetail(OrderDetail orderDetail);
	
	List<OrderDetail> getListOdDetails(String orderId);
	
	Boolean deleteAllDetail(String orderId);
}
