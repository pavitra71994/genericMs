package com.example.examMicroservice.serviceInterface;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.examMicroservice.bean.RegisterUserResponse;
import com.example.examMicroservice.bean.SendMailRequest;
import com.example.examMicroservice.bean.SendMailResponse;
import com.example.examMicroservice.bean.UserBean;
import com.example.examMicroservice.bean.UserResponse;

@RestController
@RequestMapping("/common/v1")
public interface GenericInterface {

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/sendmail", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<SendMailResponse> sendEmail(@RequestBody SendMailRequest objSendMailRequest,  @RequestHeader(name = "rootuser") String value);
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/user", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody UserBean objUserBean,  @RequestHeader(name = "rootuser") String value);

	@CrossOrigin(origins = "*")
	@GetMapping(path = "/user",  produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponse> getUser(@RequestHeader(name = "rootuser") String value);
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/user/{emailId}", produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponse> getUserByemail (String value,@PathVariable String emailId);


}
