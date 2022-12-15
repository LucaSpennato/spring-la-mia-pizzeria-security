package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.repo.IngredientRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepo ir;
	
	public void save(Ingredient i) {
		ir.save(i);
	}
	
	public List<Ingredient> findAll(){
		return ir.findAll();
	}
	
	public Optional<Ingredient> findIngredientbyId(int id){
		return ir.findById(id);
	}
	
	public void delete(Ingredient i) {
		ir.delete(i);
	}
	
	@Transactional
	public List<Ingredient> findAllPizzaInIngredients(){
		
		List<Ingredient> ingredients = ir.findAll();
		
		for (Ingredient ingredient : ingredients) {
			Hibernate.initialize(ingredient.getPizzas());
		}
		
		return ingredients;
	}
}
