package org.generation.italy.demo.pojo;

import java.util.List;

import org.generation.italy.demo.interfaces.PriceableInt;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Pizza implements PriceableInt  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Size(min=4, max=20)
	@Column(length=20)
	private String name;
	
	@Lob
	@Nullable
	@Size(max=10000)
	@Column(length=10000)
	private String description;
	
	@NotNull(message="Price cannot be null")
	@Min(value=1)
	@Column
	private int price;
	
	@OneToMany(mappedBy = "pizza", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Promozione> promotions;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Ingredient> ingredients;
	
	public Pizza() { }
	
	public Pizza(String name, String description, int price ) {
		
		setName(name);
		setDescription(description);
		setPrice(price);
		
	}
	
	public Pizza(String name, String description, int price, List<Ingredient> ingredients) {
		
		setName(name);
		setDescription(description);
		setPrice(price);
		setIngredients(ingredients);
		
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingridients) {
		this.ingredients = ingridients;
	}
	
	public void addIngredients(Ingredient ingredient) {
		
		boolean finded = false;
		for (Ingredient i : getIngredients()) 
			if (i.getId() == ingredient.getId())
				finded = true;
		
		if (!finded) 
			getIngredients().add(ingredient);
	}

	public List<Promozione> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promozione> promotions) {
		this.promotions = promotions;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	@Override
	public int getPrice() {
		return price;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {

		
		return getId() + " - " + getName() + " " + getPrice() + "$";
	}
	
}
