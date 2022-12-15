package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.repo.UserRepo;
import org.generation.italy.demo.security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServ implements UserDetailsService{

	@Autowired
	private UserRepo ur;
	
	public void save(User u) {
		ur.save(u);
	}
	
	public void deleteUser(User u) {
		ur.delete(u);
	}
	
	public List<User> findAll(){
		return ur.findAll();
	}
	
	public Optional<User> findUserById(int id){
		return ur.findById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOpt = ur.findByUsernameIgnoreCase(username);
		
		if(userOpt.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new DatabaseUserDetails(userOpt.get());
	}
	
	
	
}
