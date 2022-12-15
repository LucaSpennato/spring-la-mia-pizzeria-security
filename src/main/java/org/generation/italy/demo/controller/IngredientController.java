package org.generation.italy.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.service.IngredientService;
import org.generation.italy.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

	@Autowired
	private IngredientService is;
	@Autowired
	private PizzaService ps;
	
	@GetMapping
	public String getIngredientPage(Model model) {
		
		List<Ingredient> i = is.findAll();
		List<Pizza> p = ps.findAll();
		
		model.addAttribute("ingr",i);
		model.addAttribute("pizzas",p);
		
		return "many/ingredients";
	}
	
	
	@GetMapping("/create")
	public String getIngrCreate(Model model) {
		
		Ingredient i = new Ingredient();
		List<Pizza> pizzas = ps.findAll();
		
		model.addAttribute("ing", i);
		model.addAttribute("pizzas",pizzas);
		
		return "many/createIngr";
	}
	
	@PostMapping("/store")
	public String storeIngredient(@Valid Ingredient Ingredient) {
		
		List<Pizza> pizzas = Ingredient.getPizzas();
		
		for (Pizza p : pizzas) {
			
			p.getIngredients().add(Ingredient);
			
		}
		
		is.save(Ingredient);
		
		return "redirect:/ingredient";
	}
	
	@GetMapping("/edit/{id}")
	public String editIngredients(@PathVariable("id") int id, Model model) {
		
		Optional<Ingredient> opt = is.findIngredientbyId(id);
		
		Ingredient i = opt.get();
		
		model.addAttribute("ing",i);
		List<Pizza> pizzas = ps.findAll();
		model.addAttribute("pizzas",pizzas);
		
		return "many/editIngr";
	}
	
	
	@PostMapping("/update")
	public String updateIngredient(@Valid Ingredient ingredient) {
		
		Optional<Ingredient> opt = is.findIngredientbyId(ingredient.getId());
		
		if (!opt.isEmpty()) {
			Ingredient ingr = opt.get();
			for (Pizza pizza : ingr.getPizzas()) {
				pizza.getIngredients().remove(ingr);
			}
		}
		
		List<Pizza> pizzas = ingredient.getPizzas();
		if (pizzas != null) {
			for (Pizza pizza : pizzas) {
				pizza.getIngredients().add(ingredient);
			}
		}
//		List<Pizza> pizzas = ingredient.getPizzas();
//		System.err.println("CONTROLLER------------------------------");
//		System.err.println(ingredient);
//		
//		for (Pizza p : pizzas) {
//			
//			System.err.println("TESTING-----------------------------------------------");
//			System.err.println(p);
//			
//			p.addIngredients(ingredient);
////			p.setIngredients(Arrays.asList(new Ingredient[] {ingredient}));				
//			
//			
//		}
		
		
		is.save(ingredient);
		
		return "redirect:/ingredient";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable("id") int id) {
		
		Optional<Ingredient> opt = is.findIngredientbyId(id);
		Ingredient ingr = opt.get();
		
		if (!opt.isEmpty()) {
			for (Pizza pizza : ingr.getPizzas()) {
				pizza.getIngredients().remove(ingr);
			}
		}
		
		is.delete(ingr);
		
		
		return "redirect:/ingredient";
	}
}
