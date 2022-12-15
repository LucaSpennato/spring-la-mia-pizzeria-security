package org.generation.italy.demo.pojo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Formatter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Promozione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@NotNull
	private LocalDate startDate;
	
	@Column
	@NotNull
	private LocalDate endDate;
	
	@Column(unique = true)
	@NotBlank(message = "Deve esserci un titolo")
	@NotEmpty(message = "il titolo non può essere vuoto")
	private String title;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private Pizza pizza;
	
	public Promozione() { }
	
	public Promozione(String title, String startDate, String endDate, Pizza p) {
		setTitle(title);
		setStartDate(startDate);
		setEndDate(endDate);
		setPizza(p);
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public int getId() {
		return id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStartDate(String startDate) {
		
		LocalDate sD = LocalDate.parse(startDate);
		
		this.startDate = sD;
	}

	public void setEndDate(String endDate) {
		
		LocalDate eD = LocalDate.parse(endDate);
		this.endDate = eD;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return getTitle() + " - " + getStartDate() + " - " + getEndDate()
			+ "\n" + getPizza() ;
	}
	
}
