package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.service.IngredientService;
import org.generation.italy.demo.service.PizzaService;
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
@RequestMapping
public class PizzaController {
	
	@Autowired
	private PizzaService ps;
	
	@Autowired
	private IngredientService is;
	
	@GetMapping("/")
	public String index(Model model) {
		
		List<Pizza> pizza = ps.findAll();
		model.addAttribute("pizza", pizza);
		return "home";
	}
	
	@GetMapping("/pizza/founder/create")
	public String createPizza(Model model) {
		
		Pizza pizza = new Pizza();
		List<Ingredient> ingredients = is.findAll();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		return "create";
	}
	
	@PostMapping("/pizza/create")
	public String storePizza(@Valid Pizza pizza,
			BindingResult br, RedirectAttributes redAtr ) {
		
		if(br.hasErrors()) {
			
			System.err.println("--------------------");
			System.err.println(br.getAllErrors());
			
			redAtr.addFlashAttribute("errors", br.getAllErrors());
			
			return "redirect:/pizza/create";
		}
		ps.save(pizza);
		
		System.out.println(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/edit/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) { 
		
		Optional<Pizza> opt = ps.findPizzaById(id);
		Pizza pizza = opt.get();
		
		List<Ingredient> i = is.findAll();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients",i);
		
		return "editpizza";
	}
	
	@PostMapping("/pizza/update")
	public String updatePizza(@Valid Pizza p,
			BindingResult br, RedirectAttributes redAtr ) {
		
		if(br.hasErrors()) {
			
			System.err.println("--------------------");
			System.err.println(br.getAllErrors());
			
			redAtr.addFlashAttribute("errors", br.getAllErrors());
			
			return "redirect:/drinks/create";
		}
		
		ps.save(p);
		
		return "redirect:/";
	}
	
	@GetMapping("pizza/show/{id}")
	public String showPizza(@PathVariable("id") int id, Model model){
		
		Optional<Pizza> opt = ps.findPizzaById(id);
		Pizza pizza = opt.get();
		
		model.addAttribute("pizza", pizza);
		
		return "show";
	}
	
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		
		ps.deletePizzaById(id);
		
		return "redirect:/";
	}
	
//	@GetMapping("/pizza/search")
//	public String searchPizza(Model model,
//				@RequestParam(name="query", required=false)String query) {
//		List<Pizza> pizzas = null;
//		if(query == null) {
//			pizzas = ps.findAll();
//		}else {
//			pizzas = ps.findPizzaByName(query);
//		}
//		
//		model.addAttribute("route","pizza");
//		model.addAttribute("objs",pizzas);
//		return "search";
//	}
}
