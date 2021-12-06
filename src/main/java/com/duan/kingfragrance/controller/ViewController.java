package com.duan.kingfragrance.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindPublisherPreparer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

	@GetMapping("/product/page/{pageNo}")
	public String getProduct(ModelMap model,@PathVariable(value = "pageNo",required=false) int pageNo, @RequestParam(value = "fillter_gender", required = false) String Ftgender,
			@RequestParam(value = "fillter_season", required = false) String Ftseason,
			@RequestParam(value = "fillter_money", required = false) String Ftmoney,
			@RequestParam(value = "order_by", required = false) String orderBy) {
		List<Product> lstProduct = ProductService.getAllProduct();
		List<ProductResult> lstProductResult = ProductService.getAllProductResult(lstProduct);
		List<Brand> brands = brandService.getAllBrand();
		List<ProductResult> lstProductResultUpdate;
		if (Ftgender != null) {
			lstProductResultUpdate = new ArrayList<>();
			for (ProductResult x : lstProductResult) {
				if (Ftgender.equals("nu")) {
					Ftgender = "nữ";
				}
				if (x.getProduct().getGender().equalsIgnoreCase(Ftgender)) {
					lstProductResultUpdate.add(x);
				}
			}
			lstProductResult = lstProductResultUpdate;
		}
		if (Ftmoney != null && Ftmoney.equals("1500000-3000000")) {
			lstProductResultUpdate = new ArrayList<>();
			for (ProductResult x : lstProductResult) {
				if (x.getProductDetails().get(0).getCost() >= 1500000
						&& x.getProductDetails().get(0).getCost() <= 3000000) {
					lstProductResultUpdate.add(x);
				}
			}
			lstProductResult = lstProductResultUpdate;
		} else if (Ftmoney != null && Ftmoney.equals("3000000-5000000")) {
			lstProductResultUpdate = new ArrayList<>();
			for (ProductResult x : lstProductResult) {
				if (x.getProductDetails().get(0).getCost() >= 3000000
						&& x.getProductDetails().get(0).getCost() <= 5000000) {
					lstProductResultUpdate.add(x);
				}
			}
			lstProductResult = lstProductResultUpdate;
		} else if (Ftmoney != null && Ftmoney.equals("5000000-100000000")) {
			lstProductResultUpdate = new ArrayList<>();
			for (ProductResult x : lstProductResult) {
				if (x.getProductDetails().get(0).getCost() >= 5000000
						&& x.getProductDetails().get(0).getCost() <= 100000000) {
					lstProductResultUpdate.add(x);
				}
			}
			lstProductResult = lstProductResultUpdate;
		}
		if (lstProductResult.size() == 0) {
			model.addAttribute("notFind", "Không tìm thấy sản phẩm nào!");
		}
		else {
			if (orderBy != null) {
				if (orderBy.equals("price-desc")) {
					for (int i = 0; i < lstProductResult.size(); i++) {
						for (int j = 0; j < lstProductResult.size(); j++) {
							if (lstProductResult.get(i).getProductDetails().get(0)
									.getCost() > lstProductResult.get(j).getProductDetails().get(0)
											.getCost()) {
								ProductResult temp = new ProductResult();
								temp = lstProductResult.get(i);
								lstProductResult.set(i, lstProductResult.get(j));
								lstProductResult.set(j, temp);
							}
						}
					}
				} else if (orderBy.equals("price")) {
					for (int i = 0; i < lstProductResult.size(); i++) {
						for (int j = 0; j < lstProductResult.size(); j++) {
							if (lstProductResult.get(i).getProductDetails().get(0)
									.getCost() < lstProductResult.get(j).getProductDetails().get(0)
											.getCost()) {
								ProductResult temp = new ProductResult();
								temp = lstProductResult.get(i);
								lstProductResult.set(i, lstProductResult.get(j));
								lstProductResult.set(j, temp);
							}
						}
					}
				}
			}
		}
//		/product/page/
		
		model.addAttribute("listBrand", brands);
		model.addAttribute("titleBrand", "SHOP");
		int pageSize = 12;
		int totalPage = lstProductResult.size()/pageSize+1;
		List<ProductResult> listProductResult = ProductService.getPaginationByPageNumberAndList(pageNo, lstProductResult, pageSize);		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalItems", pageSize);
		model.addAttribute("totalPages", totalPage);
		model.addAttribute("lstProductResult", listProductResult);
		String href ="/product/page/";
		model.addAttribute("href", href);
		return "product";

	}

	@GetMapping("/product/thuonghieu/{brandName}/page/{pageNo}")
	public String getProductByBrand(@PathVariable (value="pageNo",required=false) int pageNo,@PathVariable(value = "brandName") String brandName, ModelMap model,
			@RequestParam(value = "fillter_gender", required = false) String Ftgender,
			@RequestParam(value = "fillter_season", required = false) String Ftseason,
			@RequestParam(value = "fillter_money", required = false) String Ftmoney,
			@RequestParam(value = "order_by", required = false) String orderBy) {
		List<Product> lstProduct = ProductService.getAllProduct();
		List<ProductResult> lstProductResult = ProductService.getAllProductResult(lstProduct);

		List<ProductResult> lstProductResultByBrandName = new ArrayList<>();
		for (ProductResult x : lstProductResult) {
			if (x.getProduct().getBrand().equals(brandName.replace("-", " "))) {
				lstProductResultByBrandName.add(x);
			}
		}
		List<ProductResult> lstProductResultUpdate;
		if (Ftgender != null) {
			lstProductResultUpdate = new ArrayList<>();
			for (ProductResult x : lstProductResultByBrandName) {
				if (Ftgender.equals("nu")) {
					Ftgender = "nữ";
				}
				if (x.getProduct().getGender().equalsIgnoreCase(Ftgender)) {
					lstProductResultUpdate.add(x);
				}
			}
			lstProductResultByBrandName = lstProductResultUpdate;
		}
		if (Ftmoney != null && Ftmoney.equals("1500000-3000000")) {
			lstProductResultUpdate = new ArrayList<>();
			for (ProductResult x : lstProductResultByBrandName) {
				if (x.getProductDetails().get(0).getCost() >= 1500000
						&& x.getProductDetails().get(0).getCost() <= 3000000) {
					lstProductResultUpdate.add(x);
				}
			}
			lstProductResultByBrandName = lstProductResultUpdate;
		} else if (Ftmoney != null && Ftmoney.equals("3000000-5000000")) {
			lstProductResultUpdate = new ArrayList<>();
			for (ProductResult x : lstProductResultByBrandName) {
				if (x.getProductDetails().get(0).getCost() >= 3000000
						&& x.getProductDetails().get(0).getCost() <= 5000000) {
					lstProductResultUpdate.add(x);
				}
			}
			lstProductResultByBrandName = lstProductResultUpdate;
		} else if (Ftmoney != null && Ftmoney.equals("5000000-100000000")) {
			lstProductResultUpdate = new ArrayList<>();
			for (ProductResult x : lstProductResultByBrandName) {
				if (x.getProductDetails().get(0).getCost() >= 5000000
						&& x.getProductDetails().get(0).getCost() <= 100000000) {
					lstProductResultUpdate.add(x);
				}
			}
			lstProductResultByBrandName = lstProductResultUpdate;
		}
		if (lstProductResultByBrandName.size() == 0) {
			model.addAttribute("notFind", "Không tìm thấy sản phẩm nào!");
		} else {
			if (orderBy != null) {
				if (orderBy.equals("price-desc")) {
					for (int i = 0; i < lstProductResultByBrandName.size(); i++) {
						for (int j = 0; j < lstProductResultByBrandName.size(); j++) {
							if (lstProductResultByBrandName.get(i).getProductDetails().get(0)
									.getCost() > lstProductResultByBrandName.get(j).getProductDetails().get(0)
											.getCost()) {
								ProductResult temp = new ProductResult();
								temp = lstProductResultByBrandName.get(i);
								lstProductResultByBrandName.set(i, lstProductResultByBrandName.get(j));
								lstProductResultByBrandName.set(j, temp);
							}
						}
					}
				} else if (orderBy.equals("price")) {
					for (int i = 0; i < lstProductResultByBrandName.size(); i++) {
						for (int j = 0; j < lstProductResultByBrandName.size(); j++) {
							if (lstProductResultByBrandName.get(i).getProductDetails().get(0)
									.getCost() < lstProductResultByBrandName.get(j).getProductDetails().get(0)
											.getCost()) {
								ProductResult temp = new ProductResult();
								temp = lstProductResultByBrandName.get(i);
								lstProductResultByBrandName.set(i, lstProductResultByBrandName.get(j));
								lstProductResultByBrandName.set(j, temp);
							}
						}
					}
				}
			}
		}

		model.addAttribute("titleBrand", brandName);
		List<Brand> brands = brandService.getAllBrand();
		model.addAttribute("listBrand", brands);
		int pageSize = 12;
		int totalPage = lstProductResultByBrandName.size()/pageSize+1;
		List<ProductResult> listProductResult = ProductService.getPaginationByPageNumberAndList(pageNo, lstProductResultByBrandName, pageSize);		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalItems", pageSize);
		model.addAttribute("totalPages", totalPage);
		model.addAttribute("lstProductResult", listProductResult);
		String href ="/product/thuonghieu/"+brandName+"/page/";
		model.addAttribute("href", href);
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
