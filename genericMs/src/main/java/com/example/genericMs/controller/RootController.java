package com.example.genericMs.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
