package com.duan.kingfragrance.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.kingfragrance.model.Blog;
import com.duan.kingfragrance.model.Product;
import com.duan.kingfragrance.repository.BlogRepository;
import com.duan.kingfragrance.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogRepository BlogRepo;

	@Override
	public Blog createBLog(Blog blog) {
		Blog Optional = blog;
		if (Optional == null) {
			return null;
		} else {
			Blog result = BlogRepo.save(blog);
			return result;
		}
	}

	@Override
	public List<Blog> getAllBlog() {
		List<Blog> listBlog = BlogRepo.findAll();
		if (listBlog.isEmpty()) {
			return null;
		}
		return listBlog;
	}

	@Override
	public Boolean deleteBlogById(String id) {
			Optional<Blog> optional = BlogRepo.findByBlog(id);
			if (optional.isPresent()) {
				BlogRepo.deleteById(id);
				return true;
			} else {
				return false;
			}
	}

	@Override
	public Blog updateBlog(Blog blog) {
		Blog BlogUpdate = blog;
		if (BlogUpdate==null) {
			return null;
		}
		return BlogRepo.save(BlogUpdate);
	}

	@Override
	public Blog getOneBlog(String id) {
		Optional<Blog> optional = BlogRepo.findByBlog(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
