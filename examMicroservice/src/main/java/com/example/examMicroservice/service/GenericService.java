package com.example.examMicroservice.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.examMicroservice.bean.SendMailRequest;
import com.example.examMicroservice.bean.SendMailResponse;
import com.example.examMicroservice.bean.UserBean;
import com.example.examMicroservice.bean.UserResponse;
import com.example.examMicroservice.dao.GenericDAO;

@Service
public class GenericService {
	@Autowired
	GenericDAO objGenericDAO;
	
	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

	public static String generateNewToken() {
	    byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}
	
	public String registerUser(UserBean objUserBean) {
		String uri = "https://secureroute-genericms.apps.ca-central-1.starter.openshift-online.com/common/v1/user" + objUserBean.getEmailId();
		//String uri = "http://localhost:8080/common/v1/user/" + objUserBean.getEmailId();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("rootuser", "ragnar");
		headers.set("Content-Type", "application/json");
		HttpEntity<UserBean> entity = new HttpEntity<>(objUserBean ,headers);
		UserResponse result = restTemplate.getForObject(uri, UserResponse.class, entity);
		
		if(result.getObjErrorDTO().getErrorCode().equals("304")) {
			String generatedAuthToken = generateNewToken();
			System.out.println("generatedAuthToken>>>"+generatedAuthToken);
			objUserBean.setAuthToken(generatedAuthToken);
			objGenericDAO.save(objUserBean);
			return "201";
		} else {
			return "304";
		}
		
	}

	public ArrayList<UserBean> getUser() {

		return (ArrayList<UserBean>) objGenericDAO.findAll();
		// TODO Auto-generated method stub
		
	}

	public ArrayList<UserBean> getUserByEmailid(String emailId) {
		// TODO Auto-generated method stub
		UserBean person = new UserBean();                         
		person.setEmailId(emailId);   
		return (ArrayList<UserBean>) objGenericDAO.findAll(Example.of(person));
	}
}
