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
}