package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.repo.PizzaRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepo P;
	
	public void save(Pizza pizza) {
		P.save(pizza);
	}
	
	public List<Pizza> findAll() {
		return P.findAll();
	}
	
	public Optional<Pizza> findPizzaById(int id) {
		return P.findById(id);
	}
	
	public void deletePizzaById(int id) {
		P.deleteById(id);
	}
	
	public List<Pizza> findPizzaByName(String name){
		return P.findByNameContainingIgnoreCase(name);
	}
	
	@Transactional
	public List<Pizza> findAllPromotions(){
		
		List<Pizza> pizzas = P.findAll();
		
		for (Pizza pizza : pizzas) {
			Hibernate.initialize(pizza.getPromotions());
		}
		
		return pizzas;
		
	}
}