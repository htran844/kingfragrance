package com.duan.kingfragrance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminViewController {

	@GetMapping("/admin")
	public String getAdmin(Model model) {
		return "admin-base";
	}
	@GetMapping("/admin/add-product")
	public String getAdminAddProduct(Model model) {
		return "admin-add-product";
	}
	@GetMapping("/admin/products")
	public String getAdminProduct(Model model) {
		return "admin-products";
	}
	@GetMapping("/admin/products/{slug}")
	public String getAdminEditProduct(Model model) {
		return "admin-edit-product";
	}
	@GetMapping("/admin/orders")
	public String getAdminOrder(Model model) {
		return "admin-orders";
	}
	@GetMapping("/invoices/{id}")
	public String getInvoices(Model model) {
		return "invoices";
	}
	@GetMapping("/admin/orders/{orderId}")
	public String getAdminOrderDetail(Model model) {
		return "admin-order-detail"; 
	}
}
