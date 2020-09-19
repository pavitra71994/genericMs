package com.example.examMicroservice.serviceInterface;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.examMicroservice.bean.CreateQuestionResponse;
import com.example.examMicroservice.bean.QuestionBean;
import com.example.examMicroservice.bean.QuestionResponse;
import com.example.examMicroservice.bean.ResultBodyRequest;
import com.example.examMicroservice.bean.SendMailResponse;

@RestController
@RequestMapping("quiz/v1")
public interface ServiceInterface {

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/question", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CreateQuestionResponse> createQuestionLog(@RequestBody QuestionBean objQuestionBean);

	@CrossOrigin(origins = "*")
	@GetMapping(path = "/question", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<QuestionResponse> fecthQuestionLog();
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/sendResultMail", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<SendMailResponse> sendResultViaMail(@RequestBody ResultBodyRequest objResultBodyRequest,@RequestHeader(name = "rootuser") String value );

}
