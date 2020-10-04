package com.boot.addressBook.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.addressBook.DAO.addressBookRepo;
import com.boot.addressBook.model.addressBook;






@RestController
public class addressBookController {
	
	@Autowired
	addressBookRepo repo;
	
	//Get Method
	@GetMapping(path="/contacts",produces= {"application/json"}) //produces keyword restricts the server to provide only specific format
	public List<addressBook> getAliens() {
		return repo.findAll();
	}
	
	//REST Post Method
	@PostMapping(path="/contact",consumes= {"application/json"})
 //instead of using ResponseBody at every method we can simply Write @RestController in place of @Controller at the begining of class
	public addressBook addContact(@RequestBody addressBook ab) {
		repo.save(ab);
		return ab;
	}
	
	//Rest Delete
	@DeleteMapping("/contact/{id}")
	public String contactDelete(@PathVariable int id) {
		addressBook a = repo.getOne(id);
		
		repo.delete(a);
		
		return "Deleted";
	}
	
	//REST Update Method
	@PutMapping("/contact/{id}")
	public addressBook updateContact(@RequestBody addressBook al,@PathVariable int id) {
		Optional<addressBook> a = repo.findById(id);
		al.setId(id);
		repo.save(al);
		return al;
	}

}
