package com.example.genericMs.controller;

import java.net.URI;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.genericMs.bean.PersonBean;

@RestController
public class RootController {

	@GetMapping("/person")
	public ArrayList<PersonBean>PersonBean(){
		
		ArrayList<PersonBean> arr =new ArrayList<PersonBean>();
		PersonBean obj = new PersonBean("Pavitra", "07/12/1994", "Accenture");
		arr.add(obj);
		return arr;
	}
	
	@GetMapping("/person")
	public ArrayList<PersonBean>sendSMS(){
		RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "https://api.textlocal.in/send/";
	    URI uri = new URI(baseUrl);
	     
	    Employee employee = new Employee(null, "Adam", "Gilly", "test@email.com");
	 
	    ResponseEntity<String> result = restTemplate.postForEntity(uri, employee, String.class);
	     
	    //Verify request succeed
	    Assert.assertEquals(201, result.getStatusCodeValue());
	}
}
