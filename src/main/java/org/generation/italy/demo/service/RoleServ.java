package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServ {

	@Autowired
	private RoleRepo rp;
	
	public void save(Role r) {	
		rp.save(r);
	}
	
	public void delete(Role r) {
		rp.delete(r);
	}
	
	public List<Role> findAll(){
		return rp.findAll();
	}
	
	public Optional<Role> findRolebyId(int id){
		return rp.findById(id);
	}
	
}
