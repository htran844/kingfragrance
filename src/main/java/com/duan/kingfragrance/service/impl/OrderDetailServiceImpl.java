package com.duan.kingfragrance.service.impl;

import java.util.List;
import java.util.Optional;

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
	@Override
	public Boolean updateOrderDetail(OrderDetail orderDetail) {
//		Optional<OrderDetail> op = orderDetailRepo.findById(orderDetail.getId());
//		if (op.isPresent()) {
//			OrderDetail od = op.get();
//			od.setCapacity(orderDetail.getCapacity());
//			od.setCost(orderDetail.getCost());
//			od.setOrderId(orderDetail.getOrderId());
//			od.setProductId(orderDetail.getProductId());
//			od.setQuantity(orderDetail.getQuantity());
//			od.setTotal(orderDetail.getTotal());
//			orderDetailRepo.save(od);
//			return true;
//		} else {
//			return false;
//		}
		orderDetailRepo.save(orderDetail);
		return true;
	}
	@Override
	public Boolean deleteAllDetail(String orderId) {
	List<OrderDetail> lst =	orderDetailRepo.getlstOdDetails(orderId);
	for (OrderDetail orderDetail : lst) {
		orderDetailRepo.deleteById(orderDetail.getId());
	}
		return true;
	}

}
