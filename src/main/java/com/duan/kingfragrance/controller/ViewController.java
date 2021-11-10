package com.duan.kingfragrance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.duan.kingfragrance.model.Brand;
import com.duan.kingfragrance.service.BrandService;

@Controller
public class ViewController {

	@Autowired
	private BrandService brandService;
	
	@GetMapping("/thuong-hieu")
	public String getThuongHieu(Model model) {		
	List<Brand> brands = brandService.getAllBrand();
	model.addAttribute("listBrand", brands);
	return "brand";
	}
	@GetMapping("/trang-chu")
	public String getTrangChu() {
		return "index";
	}
	
	@GetMapping("/tai-khoan")
	public String getTaiKhoan() {
		return "account";
	}
	@GetMapping("/blog")
	public String getBlog() {
		return "blog";
	}
	@GetMapping("/gio-hang")
	public String getGioHang() {
		return "cart";
	}
	@GetMapping("/check-out")
	public String getCheckOut() {
		return "checkout";
	}

	@GetMapping("/product")
	public String getProduct() {
		return "product";
	}
	@GetMapping("/product-detailts")
	public String getProductDetail() {
		return "productDetail";
	}
	
}
