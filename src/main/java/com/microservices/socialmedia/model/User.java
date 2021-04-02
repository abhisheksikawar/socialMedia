package com.microservices.socialmedia.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("This is social Media applicattion")
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ApiModelProperty("name should be atleast of 2 charachters")
	@Size(min=2,max = 30,message = "name must be atleast 2 characters")
	private String name;
	
	@ApiModelProperty("birthdate should be of past only")
	@Past(message = "birthdate must be of past")
	private Date birthDate;
	
	
	@OneToMany(mappedBy = "user")
	private List<Post> post;
	
	public User() {
		
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


	public List<Post> getPost() {
		return post;
	}


	public void setPost(List<Post> post) {
		this.post = post;
	}
	
	
	
	

}
