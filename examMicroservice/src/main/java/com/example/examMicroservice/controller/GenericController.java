package com.example.examMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import com.example.examMicroservice.bean.ErrorDTO;
import com.example.examMicroservice.bean.SendMailRequest;
import com.example.examMicroservice.bean.SendMailResponse;
import com.example.examMicroservice.serviceInterface.GenericInterface;

@Controller
public class GenericController implements GenericInterface {

	@Autowired
	private JavaMailSender javaMailSender;

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

}
