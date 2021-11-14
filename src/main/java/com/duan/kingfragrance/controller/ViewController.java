package com.duan.kingfragrance.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.FindPublisherPreparer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duan.kingfragrance.model.Brand;
import com.duan.kingfragrance.model.Product;
import com.duan.kingfragrance.model.ProductResult;
import com.duan.kingfragrance.service.BrandService;
import com.duan.kingfragrance.service.impl.ProductServiceImpl;

@Controller
public class ViewController {

	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductServiceImpl ProductServiceImpl;

	@GetMapping("/thuong-hieu")
	public String getThuongHieu(Model model) {
		List<Brand> brands = brandService.getAllBrand();
		model.addAttribute("listBrand", brands);
		return "brand";
	}

	@GetMapping("/")
	public String getTrangChu(Model model) {
		List<ProductResult> list = ProductServiceImpl.getAllProductResult();
		model.addAttribute("listProductResult", list);
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
	public String getProduct(Model model) {
		return fintPaginated(1, model);
	}

	// hùng làm
	@GetMapping("/product/page/{pageNo}")
	public String fintPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 16;
		List<ProductResult> list = ProductServiceImpl.getAllProductResult();
		Page<Product> page = ProductServiceImpl.findPaginated(pageNo, pageSize);
		List<Product> products = page.getContent();
		List<ProductResult> listProductResult = new ArrayList<ProductResult>();

		for (ProductResult x : list) {
			for (Product y : products) {
				if (x.getId().equals(y.getId())) {
					listProductResult.add(x);
				}
			}
		}
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listProductResult", listProductResult);
		return "product";

	}

	@GetMapping("/product-detailts")
	public String getProductDetail() {
		return "productDetail";
	}

}
