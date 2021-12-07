package com.duan.kingfragrance.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blog")
public class Blog {
	@Id
	private String id;
	private String title;
	public String image;
	private String content;
	public Blog(String id, String title, String image, String content) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.content = content;
	}
	public Blog() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
