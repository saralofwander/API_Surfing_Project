package com.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(description = "Add new user request model")

@Data
@Entity
@Table(name = "User")
public class User {


	@ApiModelProperty(notes = "The unique identifier of an user")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ApiModelProperty(notes = "The FirstName of the user")
	@Column(name = "first_name")
	private String firstName;

	@ApiModelProperty(notes = "The LastName of the user")
	@Column(name = "last_name")
	private String lastName;

	@ApiModelProperty(notes = "The date the user was created")
	@Column(name = "created_at")
	private Date createdAt;

	@ApiModelProperty(notes = "The date the user was last updated")
	@Column(name = "updated_at")
	private Date updatedAt;

	@OneToOne
	@JoinColumn(name = "beach_id")
	private Beach beach;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Beach getBeach() {
		return beach;
	}

	public void setBeach(Beach beach) {
		this.beach = beach;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public User(Long id, String firstName, String lastName, Date createdAt, Date updatedAt, Beach beach,
			List<Post> posts) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.beach = beach;
		this.posts = posts;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
