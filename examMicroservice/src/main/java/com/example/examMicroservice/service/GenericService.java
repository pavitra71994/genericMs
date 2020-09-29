package com.example.examMicroservice.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.examMicroservice.bean.SendMailRequest;
import com.example.examMicroservice.bean.UserBean;
import com.example.examMicroservice.bean.UserResponse;
import com.example.examMicroservice.dao.GenericDAO;
import com.example.examMicroservice.exceptions.NewApplicationException;

@Service
public class GenericService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	GenericDAO objGenericDAO;
	
	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

	public static String generateNewToken() {
	    byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}
	
	public void registerUser(UserBean objUserBean) throws NewApplicationException {
		String uri = "https://secureroute-genericms.apps.ca-central-1.starter.openshift-online.com/common/v1/user/filter?emailId=" + objUserBean.getEmailId();
		//String uri = "http://localhost:8080/common/v1/user/filter?emailId=" + objUserBean.getEmailId();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("rootuser", "ragnar");
		headers.set("Content-Type", "application/json");
		HttpEntity entity = new HttpEntity<>(headers);
		ResponseEntity<UserResponse> result = restTemplate.exchange(uri, HttpMethod.GET,entity, UserResponse.class);
		
		if(result.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
			String generatedAuthToken = generateNewToken();
			System.out.println("generatedAuthToken>>>"+generatedAuthToken);
			objUserBean.setAuthToken(generatedAuthToken);
			objGenericDAO.save(objUserBean);
		} else {
			throw new NewApplicationException("Email Already Registered");
		}
		
	}

	public ArrayList<UserBean> getUser() {

		return (ArrayList<UserBean>) objGenericDAO.findAll();
		// TODO Auto-generated method stub
		
	}

	public ArrayList<UserBean> getFilteredUser(String emailId, String password,String authCookie) throws NewApplicationException {
		// TODO Auto-generated method stub
		UserBean person = new UserBean();   
		try {
			if(emailId != null && !emailId.equals("")) {
				person.setEmailId(emailId);   
			}
			
			if(password != null && !password.equals("")) {
				person.setPassword(password);   
			}
			
			if(authCookie != null && !authCookie.equals("")) {
				person.setAuthToken(authCookie);   
			}
		} catch(Exception e) {
			throw new NewApplicationException(e.getMessage());
		}
		
		return (ArrayList<UserBean>) objGenericDAO.findAll(Example.of(person));
	}

	public void sendMail(SendMailRequest objSendMailRequest) {
		// TODO Auto-generated method stub
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(objSendMailRequest.getFromMail());
		msg.setTo(objSendMailRequest.getToEmail());
		msg.setSubject(objSendMailRequest.getSubjectLine());
		msg.setText(objSendMailRequest.getMessageBody());
		javaMailSender.send(msg);
}
}
