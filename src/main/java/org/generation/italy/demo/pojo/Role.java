package org.generation.italy.demo.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	@NotNull
	@NotBlank
	private String name;
	
	public Role() { }
	
	public Role(String name) {
		setName(name);
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		
		return getId();
	}
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Role)) return false;
		
		return obj.hashCode() == hashCode();
	}
	
}
