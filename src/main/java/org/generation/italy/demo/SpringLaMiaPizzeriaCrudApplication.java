package org.generation.italy.demo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.service.DrinkService;
import org.generation.italy.demo.service.IngredientService;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.PromoServ;
import org.generation.italy.demo.service.RoleServ;
import org.generation.italy.demo.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaService P;
	@Autowired
	private DrinkService d;
	@Autowired
	private PromoServ ps;
	@Autowired
	private IngredientService is;
	@Autowired
	private RoleServ roleS;
	@Autowired
	private UserServ userS;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Pizza p1 = new Pizza("Margherita", "pizzapazza" , 5);
		Pizza p2 = new Pizza("Qualcosa", "descrizione carina" , 6);
		Pizza p3 = new Pizza("FourSeason", "descrizione carina il ritorno" , 7);
		Pizza p4 = new Pizza("FourSeason", "descrizione carina il ritorno" , 7);
		
		P.save(p1);
		P.save(p2);
		P.save(p3);
		P.save(p4);
		
		// Drinks 
		
		Drink d1 = new Drink("biretta", null, 3);
		Drink d2 = new Drink("cochina?", null, 3);
		Drink d3 = new Drink("qualcosa", null, 5);
		
		d.save(d1);
		d.save(d2);
		d.save(d3);
		
		// Promo
		
		Promozione pz1 = new Promozione("Promo1", "2222-11-11", "2222-12-12", p1);
		Promozione pz2 = new Promozione("Promo2", "2222-11-11", "2222-12-12", p1);
		Promozione pz3 = new Promozione("Promo3", "2222-11-11", "2222-12-12", p3);
		
		
		ps.save(pz1);
		ps.save(pz2);
		ps.save(pz3);
		
		System.err.println(pz1);
		
//		ps.delete(pz3);
		
		System.out.println("EAGER, NO NEED FOR SERV------------------------------------------");
		
		List<Pizza> pizzas = P.findAll();
		
		for (Pizza pizza : pizzas) {
			
			System.out.println(pizza);
			
			for (Promozione pz : pizza.getPromotions()) {
				
				System.err.println("\n" + pz);
				
			}
			
		}
		
		System.out.println("Lazy but with serv method-------------------------------------------");
		
		List<Pizza> pizzasPromo = P.findAllPromotions();
		
		for (Pizza pizza : pizzasPromo) {
			
			System.out.println(pizza);
			
			for (Promozione pz : pizza.getPromotions()) {
				
				System.err.println("\n" + pz);
				
			}
			
		}
		
		// ingredienti
		
		Ingredient i1 = new Ingredient("Olive");
		Ingredient i2 = new Ingredient("Salame");
		Ingredient i3 = new Ingredient("Patatozze");
		
		is.save(i1);
		is.save(i2);
		is.save(i3);
		
		Pizza pizz = P.findPizzaById(1).get();
		
		List<Ingredient> ingr = Arrays.asList(new Ingredient[] {i1, i2 } );
			
		pizz.setIngredients(ingr);
		
		P.save(pizz);
		
		Pizza pizz1 = P.findPizzaById(2).get();
		
		List<Ingredient> ingr1 = Arrays.asList(new Ingredient[] {i3, i2 } );
			
		pizz1.setIngredients(ingr1);
		
		P.save(pizz1);
		
		Pizza pizz2 = P.findPizzaById(3).get();
		
		List<Ingredient> ingr2 = Arrays.asList(new Ingredient[] {i2 } );
			
		pizz2.setIngredients(ingr2);
		
		P.save(pizz2);
		
		
//		System.err.println("MANY TO MANY LATO PIZZA-------------------------------------------------------");
//		Pizza pizzaTest = P.findPizzaById(1).get();
//		for (Ingredient p : pizzaTest.getIngredients()) {
//			System.err.println(p);
//		}
//	
//		List<Pizza> pizzTesting = Arrays.asList(new Pizza[] {p3, p4});
//		
//		Ingredient ingrTest = is.findIngredientbyId(2).get();
//		
//		ingrTest.setPizzas(pizzTesting);
//		
//		is.save(ingrTest);
		
		
		// user and roles
		
		Role r1 = new Role("user");
		Role r2 = new Role("admin");
		Role r3 = new Role("founder");
		
		roleS.save(r1);
		roleS.save(r2);
		roleS.save(r3);
		
		User s1 = new User("gianni", "{noop}giannipws", r1);
		User s2 = new User("roberto", "{noop}robertopws", r2);
		User s3 = new User("chiara", "{noop}chiarapws", r3);
		
		userS.save(s1);
		userS.save(s2);
		userS.save(s3);
	}

}
