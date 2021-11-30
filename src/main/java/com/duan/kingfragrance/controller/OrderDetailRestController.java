package com.duan.kingfragrance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@PutMapping("/admin/order-detail/update")
	public ResponseEntity<?> updateOrderDetail(@RequestBody OrderDetail orderDetail){
		Boolean result = orderDetailService.updateOrderDetail(orderDetail);
		if (result) {
			return new ResponseEntity<>("update order detail thanh cong", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} 
	}
	@DeleteMapping("/admin/order-detail/update/{orderId}")
	public ResponseEntity<?> deleteAllOrderDetail(@PathVariable String orderId){
		Boolean result = orderDetailService.deleteAllDetail(orderId);
		if (result) {
			return new ResponseEntity<>("update order detail thanh cong", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} 
	}
}
