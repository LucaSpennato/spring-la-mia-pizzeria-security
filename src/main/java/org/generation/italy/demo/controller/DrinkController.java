package org.generation.italy.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/drinks")
public class DrinkController {

	@Autowired
	private DrinkService ds;
	
	@GetMapping
	public String getHome(Model model) {
		
		List<Drink> drinks = ds.findAll();
		
		System.out.println(drinks);
		
		model.addAttribute("drinks", drinks);
		
		return "drinks";
	}
	
	@GetMapping("/create")
	public String addDrink(Model model) {
		
		model.addAttribute("drink" ,new Drink());
		
		return "create-drinks";
	}
	
	@PostMapping("/store")
	public String storeDrink(@Valid Drink d,
			BindingResult br, RedirectAttributes redAtr ) {
		
		if(br.hasErrors()) {
			
			System.err.println("--------------------");
			System.err.println(br.getAllErrors());
			
			redAtr.addFlashAttribute("errors", br.getAllErrors());
			
			return "redirect:/drinks/create";
		}
		
		try {
			ds.saveUnique(d);			
		}catch(Exception e) {
			System.err.println("ERRROR----------------------------------------------------------------");
			System.err.println(e.getMessage());
			System.err.println("ERRROR----------------------------------------------------------------");
			
			redAtr.addFlashAttribute("uniqueException", "Name has to be unique" );
			return "redirect:/drinks/create";
		}
		
		return "redirect:/drinks";
	}
	
	@GetMapping("/edit/{id}")
	public String editDrink(@PathVariable("id") int id, Model model) {
		
		Optional<Drink> opt = ds.findDrinkById(id);
		
		Drink drink = opt.get();
		
		model.addAttribute("drink", drink);
		
		return "edit-drink";
	}
	
	@GetMapping("/show/{id}")
	public String getDrink(@PathVariable("id") int id, Model model) {
		
		Optional<Drink> opt = ds.findDrinkById(id);
		
		Drink drink = opt.get();
		
		model.addAttribute("drink", drink);
		
		return "show-drink";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteDrink(@PathVariable("id") int id) {
		
		Optional<Drink> opt = ds.findDrinkById(id);
		
		Drink d = opt.get();
		
		ds.deleteDrink(d);
		
		return "redirect:/drinks";
	}
	
//	@GetMapping("/search")
//	public String searchDrink(@RequestParam(name="query", required=false) String query,
//									Model model){
//		
//		List<Drink> drinks = null;
//		
//		if(query == null) {
//			drinks = ds.findAll();
//		}else {
//			drinks = ds.findDrinkByName(query);
//		}
//	
//		model.addAttribute("route","drinks");
//		model.addAttribute("query", query);
//		
//		model.addAttribute("objs",drinks);
//		return "search";
//	}
}
