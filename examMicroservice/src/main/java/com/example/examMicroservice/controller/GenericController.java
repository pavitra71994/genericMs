package com.example.examMicroservice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import com.example.examMicroservice.bean.ErrorDTO;
import com.example.examMicroservice.bean.RegisterUserResponse;
import com.example.examMicroservice.bean.SendMailRequest;
import com.example.examMicroservice.bean.SendMailResponse;
import com.example.examMicroservice.bean.UserBean;
import com.example.examMicroservice.bean.UserResponse;
import com.example.examMicroservice.service.GenericService;
import com.example.examMicroservice.serviceInterface.GenericInterface;

@Controller
public class GenericController implements GenericInterface {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	GenericService objGenericService;

	@Override
	public ResponseEntity<SendMailResponse> sendEmail(SendMailRequest objSendMailRequest, String value) {
		// TODO Auto-generated method stub

		SendMailResponse objSendMailResponse = new SendMailResponse();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
		if (value.equals("ragnar")) {
			ErrorDTO objErrorDTO = new ErrorDTO();
			
			// responseHeaders.set("ragnar","lodbrok");

			try {
				SimpleMailMessage msg = new SimpleMailMessage();
				msg.setFrom(objSendMailRequest.getFromMail());
				msg.setTo(objSendMailRequest.getToEmail());
				msg.setSubject(objSendMailRequest.getSubjectLine());
				msg.setText(objSendMailRequest.getMessageBody());

				objSendMailResponse.setObjSendMailRequest(objSendMailRequest);
				objErrorDTO.setErrorCode("201");
				objErrorDTO.setErrorMsg("Success");
				objSendMailResponse.setObjErrorDTO(objErrorDTO);
				javaMailSender.send(msg);

				return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(objSendMailResponse);
			} catch (Exception e) {

				objErrorDTO.setErrorCode("333");
				objErrorDTO.setErrorMsg(e.getMessage());
				objSendMailResponse.setObjErrorDTO(objErrorDTO);

				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).headers(responseHeaders)
						.body(objSendMailResponse);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders)
				.body(objSendMailResponse);
	}

	@Override
	public ResponseEntity<RegisterUserResponse> registerUser(UserBean objUserBean, String value) {
		// TODO Auto-generated method stub
		RegisterUserResponse objRegisterUserResponse = new RegisterUserResponse();
		HttpHeaders responseHeaders = new HttpHeaders();
		ErrorDTO objErrorDTO = new ErrorDTO();
		responseHeaders.set("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
		if (value.equals("ragnar")) {
		try {
			String result = objGenericService.registerUser(objUserBean);
			if(result.equals("201")) {
				objErrorDTO.setErrorCode("201");
				objErrorDTO.setErrorMsg("Success");
				objRegisterUserResponse.setObjErrorDTO(objErrorDTO);
			}else {
				objErrorDTO.setErrorCode(result);
				objErrorDTO.setErrorMsg("Email already Registered");
				objRegisterUserResponse.setObjErrorDTO(objErrorDTO);
			}
			
			objRegisterUserResponse.setObjUser(objUserBean);
			return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(objRegisterUserResponse);
		} catch(Exception e) {
			objErrorDTO.setErrorCode("333");
			objErrorDTO.setErrorMsg(e.getMessage());
			objRegisterUserResponse.setObjErrorDTO(objErrorDTO);
			objRegisterUserResponse.setObjUser(objUserBean);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).headers(responseHeaders)
					.body(objRegisterUserResponse);
		}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders)
				.body(objRegisterUserResponse);
	}

	@Override
	public ResponseEntity<UserResponse> getUser(String value) {
		// TODO Auto-generated method stub
		UserResponse objUserResponse = new UserResponse();
		HttpHeaders responseHeaders = new HttpHeaders();
		ErrorDTO objErrorDTO = new ErrorDTO();
		responseHeaders.set("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
//		if (value.equals("ragnar")) {
		try {
			objErrorDTO.setErrorCode("201");
			objErrorDTO.setErrorMsg("Success");
			objUserResponse.setObjErrorDTO(objErrorDTO);
			objUserResponse.setObjUser(objGenericService.getUser());
			return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(objUserResponse);
		} catch(Exception e) {
			objErrorDTO.setErrorCode("333");
			objErrorDTO.setErrorMsg(e.getMessage());
			objUserResponse.setObjErrorDTO(objErrorDTO);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).headers(responseHeaders)
					.body(objUserResponse);
		}
//		}
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders)
//				.body(objUserResponse);
	}

	@Override
	public ResponseEntity<UserResponse> getUserByemail(String value,String emailId) {
		// TODO Auto-generated method stub
		UserResponse objUserResponse = new UserResponse();
		HttpHeaders responseHeaders = new HttpHeaders();
		ErrorDTO objErrorDTO = new ErrorDTO();
		responseHeaders.set("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
//		if (value.equals("ragnar")) {
		try {
			
			ArrayList<UserBean> arr = objGenericService.getUserByEmailid(emailId);
			if(arr.size()>0) {
				objErrorDTO.setErrorCode("201");
				objErrorDTO.setErrorMsg("Success");
				objUserResponse.setObjErrorDTO(objErrorDTO);
			} else {
				objErrorDTO.setErrorCode("304");
				objErrorDTO.setErrorMsg("Email Not Registered");
				objUserResponse.setObjErrorDTO(objErrorDTO);
			}
			objUserResponse.setObjUser(objGenericService.getUserByEmailid(emailId));
			return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(objUserResponse);
		} catch(Exception e) {
			objErrorDTO.setErrorCode("333");
			objErrorDTO.setErrorMsg(e.getMessage());
			objUserResponse.setObjErrorDTO(objErrorDTO);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).headers(responseHeaders)
					.body(objUserResponse);
		}
//		}
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders)
//				.body(objUserResponse);
	}


}
