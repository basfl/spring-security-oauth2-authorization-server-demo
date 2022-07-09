package com;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import objects.Country;
import objects.InitializerService;

@RestController
@RequestMapping("/world")
public class Controller {
	
	@Autowired
	InitializerService initializerService;
	public Country response = null;
	
	 @GetMapping("/all")
	 List<Country> getAll(){
		 System.out.println("making a call to getALL");
		 return initializerService.getCountryList();
		 
	 }
	 @GetMapping("/country/{name}")
	 Country geCountry(@PathVariable String name){
		 
		 initializerService.getCountryList().forEach(country->{
			 if(country.getName().equals(name)) {
				response=country;
			 }
		 });
		 
		 return response;
	
		 
	 }
	 
	  @PostMapping("/country")
	  List<Country> create(@RequestBody Country newCountry) {
		initializerService.getCountryList().add(newCountry);
		return initializerService.getCountryList();
		  
		  
	  }

}
