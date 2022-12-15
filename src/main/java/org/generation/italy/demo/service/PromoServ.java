package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.repo.PromotionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoServ {

	@Autowired
	private PromotionRepo pr;
	
	public void save(Promozione p) {
		pr.save(p);
	}
	
	public List<Promozione> findAll(){	
		return pr.findAll();
	}
	
	public Optional<Promozione> findPromozioneById(int id){
		return pr.findById(id);
	}
	
	public void delete(Promozione p) {
		
		pr.delete(p);
	}
	
}
