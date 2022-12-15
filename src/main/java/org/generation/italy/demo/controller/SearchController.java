package org.generation.italy.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.service.DrinkService;
import org.generation.italy.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private DrinkService ds;
	
	@Autowired
	private PizzaService ps;
	
	@GetMapping
	public String search(@RequestParam(name="query", required=false) String query, Model model) {
		
	
		List<Drink> drinks = query == null 
				? ds.findAll()
				: ds.findDrinkByName(query);
		
		List<Pizza> pizzas = query == null 
				? ps.findAll()
				: ps.findPizzaByName(query); 
		
		
//		List<Object> objs = new ArrayList<>();
//		for (Drink d : drinks) {
//			objs.add(d);
//		}
//		for (Pizza p : pizzas) {
//			objs.add(p);
//		}

//		model.addAttribute("objs", objs);
		
		model.addAttribute("query", query);
		
		model.addAttribute("drinks",drinks);
		model.addAttribute("routeDrinks","drinks");
		
		model.addAttribute("pizzas",pizzas);
		model.addAttribute("routePizza","pizza");
		
		return "search";
	}
}
