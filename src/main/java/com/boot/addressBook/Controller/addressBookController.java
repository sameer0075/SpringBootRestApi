package com.boot.addressBook.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	@CrossOrigin(origins = "http://localhost:4200")
	public List<addressBook> getContacts() {
		return repo.findAll();
	}
	
	//Get Method
	@GetMapping(path="/update/{id}",produces= {"application/json"}) //produces keyword restricts the server to provide only specific format
	@CrossOrigin(origins = "http://localhost:4200")
	public Optional<addressBook> getContact(@PathVariable int id) {
		return repo.findById(id);
	}
	
	//REST Post Method
	@PostMapping(path="/contact",consumes= {"application/json"})
	@CrossOrigin(origins = "http://localhost:4200")
 //instead of using ResponseBody at every method we can simply Write @RestController in place of @Controller at the begining of class
	public addressBook addContact(@RequestBody addressBook ab) {
		repo.save(ab);
		return ab;
	}
	
	//Rest Delete
//	@DeleteMapping("/contact/{id}")
//	@CrossOrigin(origins = "http://localhost:4200")
//	public String contactDelete(@PathVariable int id) {
//		addressBook a = repo.getOne(id);
//		
//		repo.delete(a);
//		
//		return "Deleted";
//	}
	
	@DeleteMapping("/contact/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Map<String, Boolean> deleteContact(@PathVariable(value = "id") Integer employeeId)
			throws ResourceNotFoundException {
		addressBook employee = repo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		repo.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	//REST Update Method
	@PutMapping("/contact/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public addressBook updateContact(@RequestBody addressBook al,@PathVariable int id) {
		Optional<addressBook> a = repo.findById(id);
		al.setId(id);
		repo.save(al);
		return al;
	}

}
