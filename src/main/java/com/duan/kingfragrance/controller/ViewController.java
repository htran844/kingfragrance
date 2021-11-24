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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duan.kingfragrance.model.Brand;
import com.duan.kingfragrance.model.Product;
import com.duan.kingfragrance.model.ProductDetail;
import com.duan.kingfragrance.model.ProductFragrance;
import com.duan.kingfragrance.model.ProductRecommended;
import com.duan.kingfragrance.model.ProductResult;
import com.duan.kingfragrance.service.BrandService;
import com.duan.kingfragrance.service.ProductDetailService;
import com.duan.kingfragrance.service.ProductFraService;
import com.duan.kingfragrance.service.ProductRecService;
import com.duan.kingfragrance.service.ProductService;
import com.duan.kingfragrance.service.impl.ProductServiceImpl;

@Controller
public class ViewController {

	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductService ProductService;
	@Autowired
	private ProductFraService productFraService;
	@Autowired
	private ProductRecService productRecService;
	@Autowired
	private ProductDetailService ProductDetailService;

	@GetMapping("/thuong-hieu")
	public String getThuongHieu(Model model) {
		List<Brand> brands = brandService.getAllBrand();
		model.addAttribute("listBrand", brands);
		return "brand";
		
	}

	@GetMapping("/")
	public String getTrangChu(Model model) {
		List<ProductResult> list = ProductService.getAllProductResult(ProductService.getAllProduct());
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

	@GetMapping("/product")
	public String getProduct(Model model) {
		return fintPaginated(1, model);
	}
	@GetMapping("/product/page/{pageNo}")
	public String fintPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 16;
		Page<Product> page = ProductService.findPaginated(pageNo, pageSize);
		List<Product> lstproducts = page.getContent();
		List<ProductResult> listProductResult = new ArrayList<ProductResult>();
		List<ProductDetail> listProductDetail; 
		for (Product x : lstproducts) {
			listProductDetail= ProductDetailService.getAllProductDetailById(x.getId());
			listProductResult.add(new ProductResult(x, listProductDetail));
		}
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listProductResult", listProductResult);
		return "product";
	}

	@GetMapping("/product-detailts/{slug}")
	public String getProductDetail(@PathVariable(value = "slug") String slug, Model model) {
		ProductResult productResult = ProductService.getOneProductResultBySlug(slug);
		ProductFragrance ProductFragrance = productFraService.getProFra(productResult.getProduct().getId());
		ProductRecommended productRecommended = productRecService.getProRec(productResult.getProduct().getId());
		model.addAttribute("productDetails", productResult);
		model.addAttribute("ProductFragrance", ProductFragrance);
		model.addAttribute("productRecommended", productRecommended);
		return "productDetail";
	}

}

