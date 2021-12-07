package com.duan.kingfragrance.service;

import java.util.List;

import com.duan.kingfragrance.model.Blog;

public interface BlogService {
	public Blog createBLog(Blog blog);

	public List<Blog> getAllBlog();
	public Blog getOneBlog(String id);
	public Boolean deleteBlogById(String id);

	public Blog updateBlog( Blog blog);
}
