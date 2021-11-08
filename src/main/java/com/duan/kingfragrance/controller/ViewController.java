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
	return "thuonghieu";
	}
}
