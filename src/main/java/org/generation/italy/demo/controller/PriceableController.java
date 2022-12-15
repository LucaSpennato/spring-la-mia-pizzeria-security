package org.generation.italy.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.generation.italy.demo.interfaces.PriceableInt;
import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.service.DrinkService;
import org.generation.italy.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/priceable")
public class PriceableController {

	@Autowired
	private DrinkService ds;
	
	@Autowired
	private PizzaService ps;
	
	@GetMapping
	public String getSorted(Model model) {
		
		List<PriceableInt> objs = new ArrayList<>();
		
		for (Drink d : ds.findAll()) {
			objs.add(d);
		}
		for (Pizza p : ps.findAll()) {
			objs.add(p);
		}

		objs.sort((p1,p2)-> p2.getPrice() - p1.getPrice() );
//		objs.sort((p1,p2)-> p1.getPrice() - p2.getPrice() );
		
		model.addAttribute("objs", objs);
		
		return "priceable";
	}
	
}
