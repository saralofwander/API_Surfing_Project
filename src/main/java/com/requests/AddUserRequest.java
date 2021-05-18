package com.requests;

import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel(description = "Add new user request model")

public class AddUserRequest {
	

	@ApiModelProperty(notes = "The FirstName of the user")
	@NotBlank(message = "FirstName is mandatory")
	private String firstName;

	@ApiModelProperty(notes = "The LastName of the user")
	@NotBlank(message = "LastName is mandatory")
	private String lastName;

	private String location;
	private String beachName;

	private List<AddPostRequest> posts;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String city) {
		this.location = city;
	}

	public String getBeachName() {
		return beachName;
	}

	public void setBeachName(String beachName) {
		this.beachName = beachName;
	}

	public List<AddPostRequest> getPosts() {
		return posts;
	}

	public void setPosts(List<AddPostRequest> posts) {
		this.posts = posts;
	}

	public AddUserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
	