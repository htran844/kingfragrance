package com.duan.kingfragrance.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.duan.kingfragrance.model.Blog;
import com.duan.kingfragrance.model.Product;
import com.duan.kingfragrance.repository.BlogRepository;
import com.duan.kingfragrance.service.BlogService;

@RestController
public class BlogRestController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogRepository BlogRepo;
	@PostMapping("admin/blog-up")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,@RequestParam("id") String id) {
		Optional<Blog> optional = BlogRepo.findByBlog(id);
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
			Blog blog = optional.get();

			blog.setImage(link);
			BlogRepo.save(blog);
			return new ResponseEntity<String>(link, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(id, HttpStatus.OK);
		}

	}

	@PostMapping("admin/blog-main")
	public ResponseEntity<?> createBlog(@RequestBody Blog blog) {
		Blog result = blogService.createBLog(blog);
		if (result != null) {
			return new ResponseEntity<Blog>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	@PutMapping("admin/blog-main")
	public ResponseEntity<?> updateBlog(@RequestBody Blog blog) {
		Blog result = blogService.updateBlog(blog);
		if (result != null) {
			return new ResponseEntity<Blog>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	@GetMapping("admin/blog-mainAll")
	public ResponseEntity<?> getAllAdminBlog() {
		List<Blog> listBlog =blogService.getAllBlog();
		return new ResponseEntity<List<Blog>>(listBlog, HttpStatus.OK);
	}
	@DeleteMapping("admin/blog-main/{BlogId}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value="BlogId") String BlogId) {
		Boolean result = blogService.deleteBlogById(BlogId);
		if (result) {
			return new ResponseEntity<>("xoa thanh cong Product có id " + BlogId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	
}
