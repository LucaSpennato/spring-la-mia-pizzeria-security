package org.generation.italy.demo.pojo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.geo.Distance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(unique = true, length = 20)
	@Size(min = 3, max = 20)
	@NotBlank
	private String username;
	
	@Column
	@NotNull
	@NotBlank
	private String password;
	
	@ManyToMany
	private Set<Role> roles;
	
	public User() { }
	
	public User(String username, String password, Role role) {
		
	}
	
	public User(String username, String password, Set<Role> roles) {
		
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role r) {
		
		if(roles == null) {
			roles = new HashSet<>();
		}
		
		getRoles().add(r);
	}

	@Override
	public String toString() {
		return getId() + " - " + getUsername() + "\n" + getRoles();
	}
}
