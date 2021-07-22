package com.example.SpringbootCRUDapp.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringbootCRUDapp.model.Country;
import com.example.SpringbootCRUDapp.repository.CountryRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("rest/v2")
public class CountryController {
	@Autowired
	private CountryRepository countryRepository;
	
	//Retrieving all the available country records
	@GetMapping("/countries")
		public List<Country>getAllCountries(){
			return countryRepository.findAll();
		}
	
	
	//Retrieving particular record on a country based on ID
	@GetMapping("/country/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value="id")Long countryId)
	throws AttributeNotFoundException{
		Country country = countryRepository.findById(countryId)
				.orElseThrow(()-> new AttributeNotFoundException("This country cannot be found"));
		return ResponseEntity.ok().body(country);	
	}
	
	//Adding new country records
	@PostMapping("/countries")
	public Country createCountry(@Validated @RequestBody Country country) {
		return countryRepository.save(country);
	}
	
	//Changing details regarding an added country record
	@PutMapping("/countries/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="id") Long countryId, @Validated
			@RequestBody Country countryDetails) throws AttributeNotFoundException{
	Country country = countryRepository.findById(countryId)
			.orElseThrow(()-> new AttributeNotFoundException("Country not found in records"));
	
	country.setCountryName(countryDetails.getCountryName());
	final Country updatedCountry = countryRepository.save(country);
	return ResponseEntity.ok(updatedCountry);
	}
	
	//Deleting existing country records
	@DeleteMapping("/countries/{id}")
	public Map<String,Boolean>deleteCountry(@PathVariable(value="id")Long countryId)
	throws AttributeNotFoundException{
		Country country = countryRepository.findById(countryId)
				.orElseThrow(()-> new AttributeNotFoundException("Country not found"));
		
		countryRepository.delete(country);
		Map<String,Boolean>response=new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return response;
	}
}

