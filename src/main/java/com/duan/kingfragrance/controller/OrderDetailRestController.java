package com.duan.kingfragrance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duan.kingfragrance.model.OrderDetail;
import com.duan.kingfragrance.service.OrderDetailService;

@RestController
public class OrderDetailRestController {
	@Autowired
	private OrderDetailService orderDetailService;
	
	@PostMapping("/admin/order-detail")
	public ResponseEntity<?> createOrderDetail(@RequestBody OrderDetail orderDetail){
		Boolean result = orderDetailService.createOrderDetail(orderDetail);
		if (result) {
			return new ResponseEntity<>("tao order detail thanh cong", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} 
	}
	@GetMapping("/admin/invoices/{orderId}")
	public ResponseEntity<?> getListOrderDetail(@PathVariable String orderId){
		List<OrderDetail> lst = orderDetailService.getListOdDetails(orderId);
		if (lst.size()>0) {
			return new ResponseEntity<List<OrderDetail>>(lst, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
}
