package com.duan.kingfragrance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duan.kingfragrance.model.Order;
import com.duan.kingfragrance.service.OrderService;

@RestController
public class OrderRestController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/admin/orders/{phone}/{status}")
	public ResponseEntity<?> getAllOrder(@PathVariable String phone, @PathVariable String status){
		List<Order> orders = orderService.getAllOrder(phone, status);
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	@PostMapping("/admin/orders")
	public ResponseEntity<?> createOrder(@RequestBody Order order){
		 Order result = orderService.createOrder(order);
		
		if (result!=null) {
			return new ResponseEntity<>(result.getId(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	@PutMapping("/admin/delivery/{orderId}")
	public ResponseEntity<?> changeStatusDelivery(@PathVariable String orderId){
		Boolean result = orderService.changeStatusDelivery(orderId);
		if (result) {
			return new ResponseEntity<>("chuyển sang giao hàng thành công", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	@PutMapping("/admin/cancel/{orderId}")
	public ResponseEntity<?> changeStatusCancel(@PathVariable String orderId){
		Boolean result = orderService.changeStatusCancel(orderId);
		if (result) {
			return new ResponseEntity<>("hủy đơn thành công", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	@PutMapping("/admin/refund/{orderId}")
	public ResponseEntity<?> changeStatusRefund(@PathVariable String orderId){
		Boolean result = orderService.changeStatusRefund(orderId);
		if (result) {
			return new ResponseEntity<>("hoàn đơn thành công", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	@GetMapping("admin/order/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable String id){
		Order od = orderService.getOneOrderById(id);
		return new ResponseEntity<Order>(od, HttpStatus.OK);
	}
	@PutMapping("/admin/order/update")
	public ResponseEntity<?> updateOrder(@RequestBody Order order){
		Boolean result = orderService.updateOrder(order);
		if (result) {
			return new ResponseEntity<>("update thành công", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
}
