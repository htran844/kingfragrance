package com.duan.kingfragrance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.duan.kingfragrance.model.Blog;
import com.duan.kingfragrance.service.BlogService;
import com.duan.kingfragrance.service.UserService;

@Controller
public class AdminViewController {
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
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
	
	
	//blog
	@GetMapping("/admin/blogs")
	public String getAdminBlog(Model model) {
		return "admin-blog"; 
	}
	@GetMapping("/admin/add-blogs")
	public String getAdminAddBlog(@CookieValue(value = "token", defaultValue = "Atta") String token) {
		Boolean check = userService.verifyUser(token);
		if (check) {
			return "admin-add-blog";
		} else {
			return "login";
		}

	}
//	@GetMapping("/admin/blogs/{slug}")
//	public String getAdminEditBlog(@CookieValue(value = "token", defaultValue = "Atta") String token) {
//		Boolean check = userService.verifyUser(token);
//		if (check) {
//			return "admin-edit-blog";
//		} else {
//			return "login";
//		}
//		
//	}
	@GetMapping("admin/blogs/{id}")
	public String getBlog(@PathVariable String id,Model model) {
		Blog blog = blogService.getOneBlog(id);
		model.addAttribute("blog",blog);
		return "admin-edit-blog";
	}
	
}
