package com.example.examMicroservice.serviceInterface;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.examMicroservice.bean.CreateQuestionResponse;
import com.example.examMicroservice.bean.QuestionBean;
import com.example.examMicroservice.bean.QuestionResponse;

@RestController
public interface ServiceInterface {
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/question", 
	        consumes = "application/json", 
	        produces = "application/json")
	public ResponseEntity<CreateQuestionResponse> createQuestionLog(@RequestBody QuestionBean objQuestionBean);
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/question", 
	        consumes = "application/json", 
	        produces = "application/json")
	public ResponseEntity<QuestionResponse> fecthQuestionLog();

        @GetMapping(path = "/sendmail")
        public void sendEmail();

}
