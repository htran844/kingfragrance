package com.duan.kingfragrance.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.Query;
import javax.xml.crypto.Data;

import org.bson.json.JsonObject;
import org.cloudinary.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonProperties;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.duan.kingfragrance.model.Brand;
import com.duan.kingfragrance.model.Product;
import com.duan.kingfragrance.model.ProductDetail;
import com.duan.kingfragrance.model.ProductResult;
import com.duan.kingfragrance.repository.ProductDetailRepository;
import com.duan.kingfragrance.repository.ProductRepository;
import com.duan.kingfragrance.service.BrandService;
import com.duan.kingfragrance.service.ProductService;

@RestController
public class ProductRestController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
private ProductService ProductService;
	@Autowired 
	private BrandService brandService;
	@PostMapping("admin/product-up")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("slug") String slug) {
		Optional<Product> optional = productRepo.findBySlug(slug);
		if (optional.isPresent()) {
			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "hoang844", "api_key",
					"217959259192693", "api_secret", "RSCRmsau04ynTetZx3L3jYGNbkY", "secure", true));
			String link = "";
			try {
				Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
				link = String.valueOf(uploadResult.get("url"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Product product = optional.get();
			product.setImage(link);
			productRepo.save(product);
			return new ResponseEntity<String>(link, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}

	}

	@PostMapping("admin/product-main")
	public ResponseEntity<?> createProduct(@RequestBody Product product) {
		Product result = productService.CreateProduct(product);
		if (result != null) {
			return new ResponseEntity<Product>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
//		return new ResponseEntity<>("hihi", HttpStatus.OK);
	}

	@DeleteMapping("/admin/product-main/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
		Boolean result = productService.deleteProductById(productId);
		if (result) {
			return new ResponseEntity<>("xoa thanh cong Product có id " + productId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	@PutMapping("/admin/product-main")
	public ResponseEntity<?> updateProductCha(@RequestBody Product product) {
		Boolean result = productService.updateProduct(product);
		if (result) {
			return new ResponseEntity<>("update thanh cong Product có slug " + product.getSlug(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("khong tim thay Product có slug " + product.getSlug(), HttpStatus.OK);
		}
	}

	@GetMapping("/admin/product-main/{page}/{search}/{gender}")
	public ResponseEntity<?> getAllAdminProduct(@PathVariable int page, @PathVariable String search,
			@PathVariable String gender) {
		List<Product> listProduct = productService.getAdminProduct(page, search, gender);
		return new ResponseEntity<List<Product>>(listProduct, HttpStatus.OK);
	}

	@GetMapping("/admin/product-mainid/{id}")
	public ResponseEntity<?> getProductById(@PathVariable String id) {
		Product product = productService.getOneProductById(id);
		if (product ==null) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
	}

	@GetMapping("/getCartFromLocalStorage")
	public ResponseEntity<?> getAllProductFromLocalStorage(@RequestParam String slug,
			@RequestParam String productDetailId) {
		ProductResult productResult = productService.getProductResultBySlugAndDetailId(slug, productDetailId);
		return new ResponseEntity<>(productResult, HttpStatus.OK);
	}

	@GetMapping("/getAllProductResult")
	public ResponseEntity<?> getAllProductResult() {
		List<Product> lstProduct = productService.getAllProduct();
		List<ProductResult> lstProductResult = productService.getAllProductResult(lstProduct);
		if (lstProductResult != null) {
			return new ResponseEntity<>(lstProductResult, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

	@GetMapping("/admin/product-main/{slug}")
	public ResponseEntity<?> getProduct(@PathVariable String slug) {
		Product product = productService.getOneProduct(slug);
		if (product == null) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
	}

	@GetMapping("/getAllProductResult/{by}")
	public ResponseEntity<?> getAllProductResult_By(@PathVariable String by) {
		List<Product> lstProduct = productService.getAllProduct();
		List<ProductResult> lstProductResult = productService.getAllProductResult(lstProduct);
		if (by.equals("price")) {
			for (int i = 0; i < lstProductResult.size(); i++) {
				for (int j = 0; j < lstProductResult.size(); j++) {
					if (lstProductResult.get(i).getProductDetails().get(0).getCost() < lstProductResult.get(j)
							.getProductDetails().get(0).getCost()) {
						ProductResult temp = new ProductResult();
						temp = lstProductResult.get(i);
						lstProductResult.set(i, lstProductResult.get(j));
						lstProductResult.set(j,temp);
					}
				}	
			}
		}
		if (by.equals("price-desc")) {
			for (int i = 0; i < lstProductResult.size(); i++) {
				for (int j = 0; j < lstProductResult.size(); j++) {
					if (lstProductResult.get(i).getProductDetails().get(0).getCost() > lstProductResult.get(j)
							.getProductDetails().get(0).getCost()) {
						ProductResult temp = new ProductResult();
						temp = lstProductResult.get(i);
						lstProductResult.set(i, lstProductResult.get(j));
						lstProductResult.set(j,temp);
					}
				}	
			}
		}
		if (lstProductResult != null) {
			return new ResponseEntity<>(lstProductResult, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@GetMapping("/fillterProductBy")
	public ResponseEntity<?> getProduct(@RequestParam(value="fillter_gender" , required=false) String Ftgender
			,@RequestParam(value="fillter_season" , required=false) String Ftseason,
			@RequestParam(value="fillter_money" , required=false) String Ftmoney) {
		List<Product> lstProduct = ProductService.getAllProduct();
		List<ProductResult> lstProductResult = ProductService.getAllProductResult(lstProduct);
		List<Brand> brands = brandService.getAllBrand();
		List<ProductResult> lstProductResultUpdate ;
		if (Ftgender!=null) {
			lstProductResultUpdate = new ArrayList<>();
			for (ProductResult x : lstProductResult) {
				if (x.getProduct().getGender().equals(Ftgender)) {
					lstProductResultUpdate.add(x);
				}
			}
			lstProductResult=lstProductResultUpdate;
		}
		if (Ftmoney!=null&&Ftmoney.equals("1500000-3000000")) {
			lstProductResultUpdate = new ArrayList<>();
			for (ProductResult x : lstProductResult) {
				if (x.getProductDetails().get(0).getCost()>=1500000&&
						x.getProductDetails().get(0).getCost()<=3000000) {
					lstProductResultUpdate.add(x);
				}
			}
			lstProductResult=lstProductResultUpdate;
		}
		else if (Ftmoney!=null&&Ftmoney.equals("3000000-5000000")) {
			lstProductResultUpdate = new ArrayList<>();
			for (ProductResult x : lstProductResult) {
				if (x.getProductDetails().get(0).getCost()>=3000000&&
						x.getProductDetails().get(0).getCost()<=5000000) {
					lstProductResultUpdate.add(x);
				}
			}
			lstProductResult=lstProductResultUpdate;
		}
		else if (Ftmoney!=null&&Ftmoney.equals("5000000-100000000")) {
			lstProductResultUpdate = new ArrayList<>();
			for (ProductResult x : lstProductResult) {
				if (x.getProductDetails().get(0).getCost()>=5000000&&
						x.getProductDetails().get(0).getCost()<=100000000) {
					lstProductResultUpdate.add(x);
				}
			}
			lstProductResult=lstProductResultUpdate;
		}
		return new ResponseEntity<List<ProductResult>>(lstProductResult, HttpStatus.OK);
	}


}
