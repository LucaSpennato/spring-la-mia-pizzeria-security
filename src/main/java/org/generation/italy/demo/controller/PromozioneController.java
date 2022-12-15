package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.PromoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promozioni")
public class PromozioneController {

	@Autowired
	private PizzaService pizzaServ;
	
	@Autowired
	private PromoServ promoServ;
	
	
	@GetMapping
	public String getPizzaWithPromo(Model model) {
		
		List<Promozione> promos = promoServ.findAll();
		List<Pizza> pizzas = pizzaServ.findAll();
		 
		model.addAttribute("promos", promos);
		model.addAttribute("pizzas",pizzas);
		
		return "pizzapromo";
	}
	
	@GetMapping("/create")
	public String savePromo(Model model) {
		
		Promozione promo = new Promozione();
		
		List<Pizza> pizzas = pizzaServ.findAll();
		
		model.addAttribute("promo", promo);
		
		model.addAttribute("pizzas", pizzas);
		
		return "createPromo";
	}
	
	@PostMapping("/store")
	public String storePromo(@Valid Promozione promo) {

		promoServ.save(promo);
		
		return "redirect:/promozioni";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		
		Optional<Promozione> opt = promoServ.findPromozioneById(id);
		Promozione p = opt.get();
		
		promoServ.delete(p);
		
		return "redirect:/promozioni";
	}
	
	@GetMapping("/create/pizza-promo")
	public String createPizzaPromo(Model model) {
		
		Pizza p = new Pizza();
		List<Promozione> promozioni = promoServ.findAll();
		
		model.addAttribute("pizza", p);
		model.addAttribute("promozioni",promozioni);
		
		return "create-pizza-promo";
	}
	
	@PostMapping("/store/pizza-promo")
	public String storePizzaPromo(@Valid Pizza pizza) {
		
		List<Promozione> pizzaPromos = pizza.getPromotions();
		
		for (Promozione promozione : pizzaPromos) {
			promozione.setPizza(pizza);
		}

		pizzaServ.save(pizza);
		
		return "redirect:/promozioni";
	}
}