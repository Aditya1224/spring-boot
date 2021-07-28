package com.rest.webservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user")  
@Entity
public class User {

	@Id//making Id as primary key
	@GeneratedValue	
	private Integer id;
	
	@Size(min=5, message = "Name should have atleast 2 characters")// Field validation
	@ApiModelProperty(notes="name should have atleast 5 characters") 
	private String name;
	@Past  
	@ApiModelProperty(notes="Birth date should be in the past") 
	private Date birthDate;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}
