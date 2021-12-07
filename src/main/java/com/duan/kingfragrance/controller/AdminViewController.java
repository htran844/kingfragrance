package com.duan.kingfragrance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import com.duan.kingfragrance.service.UserService;

@Controller
public class AdminViewController {
	@Autowired
	private UserService userService;
	@GetMapping("/admin")
	public String getAdmin(@CookieValue(value = "token", defaultValue = "Atta") String token) {
		Boolean check = userService.verifyUser(token);
		if (check) {
			return "admin-base";
		} else {
			return "login";
		}
		
	}
	@GetMapping("/admin/add-product")
	public String getAdminAddProduct(@CookieValue(value = "token", defaultValue = "Atta") String token) {
		Boolean check = userService.verifyUser(token);
		if (check) {
			return "admin-add-product";
		} else {
			return "login";
		}

	}
	@GetMapping("/admin/products")
	public String getAdminProduct(@CookieValue(value = "token", defaultValue = "Atta") String token) {
		Boolean check = userService.verifyUser(token);
		if (check) {
			return "admin-products";
		} else {
			return "login";
		}
		
	}
	@GetMapping("/admin/products/{slug}")
	public String getAdminEditProduct(@CookieValue(value = "token", defaultValue = "Atta") String token) {
		Boolean check = userService.verifyUser(token);
		if (check) {
			return "admin-edit-product";
		} else {
			return "login";
		}
		
	}
	@GetMapping("/admin/orders")
	public String getAdminOrder(@CookieValue(value = "token", defaultValue = "Atta") String token) {
		Boolean check = userService.verifyUser(token);
		if (check) {
			return "admin-orders";
		} else {
			return "login";
		}
		
	}
	@GetMapping("/invoices/{id}")
	public String getInvoices() {
		return "invoices";
	}
	@GetMapping("/admin/orders/{orderId}")
	public String getAdminOrderDetail(@CookieValue(value = "token", defaultValue = "Atta") String token) {
		Boolean check = userService.verifyUser(token);
		if (check) {
			return "admin-order-detail"; 
		} else {
			return "login";
		}
		
	}
	@GetMapping("/admin/account")
	public String getAdminAccount(@CookieValue(value = "token", defaultValue = "Atta") String token) {
		Boolean check = userService.verifyUser(token);
		if (check) {
			return "admin-account"; 
		} else {
			return "login";
		}
		
	}
	@GetMapping("/admin/login")
	public String getAdminLogin(Model model) {
		return "login"; 
	}
}
