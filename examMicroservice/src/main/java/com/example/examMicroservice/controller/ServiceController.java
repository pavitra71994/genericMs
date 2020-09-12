package com.example.examMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.examMicroservice.bean.CreateQuestionResponse;
import com.example.examMicroservice.bean.ErrorDTO;
import com.example.examMicroservice.bean.QuestionBean;
import com.example.examMicroservice.bean.QuestionResponse;
import com.example.examMicroservice.service.ExamService;
import com.example.examMicroservice.serviceInterface.ServiceInterface;

@Component
public class ServiceController implements ServiceInterface {

	@Autowired
	ExamService objExamService;
	
	@Override
	public ResponseEntity<CreateQuestionResponse> createQuestionLog(QuestionBean objQuestionBean) {
		// TODO Auto-generated method stub
		
		CreateQuestionResponse objCreateQuestionResponse =new CreateQuestionResponse();
		ErrorDTO objErrorDTO =  new ErrorDTO();
		try {
			objExamService.createQuestionLog(objQuestionBean);
			objCreateQuestionResponse.setObjQuestionBean(objQuestionBean);
			
			objErrorDTO.setErrorCode("200");
			objErrorDTO.setErrorMsg("Success");
			objCreateQuestionResponse.setObjErrorDTO(objErrorDTO);
			return ResponseEntity.status(HttpStatus.OK)
			        .body(objCreateQuestionResponse);
		} catch(Exception e) {
			objCreateQuestionResponse.setObjQuestionBean(objQuestionBean);
			objErrorDTO.setErrorCode("333");
			objErrorDTO.setErrorMsg(e.getMessage());
			objCreateQuestionResponse.setObjErrorDTO(objErrorDTO);
			return ResponseEntity.status(HttpStatus.CREATED)
			        .body(objCreateQuestionResponse);
		}
		 
		
	}

	@Override
	public ResponseEntity<QuestionResponse> fecthQuestionLog() {
		// TODO Auto-generated method stub

		QuestionResponse objQuestionResponse = new QuestionResponse();
		ErrorDTO objErrorDTO =  new ErrorDTO();
		try {
			objErrorDTO.setErrorCode("200");
			objErrorDTO.setErrorMsg("Success");
			objQuestionResponse.setObjErrorDTO(objErrorDTO);
			objQuestionResponse.setQuestionAns(objExamService.fetchQuestionLog());
			return ResponseEntity.status(HttpStatus.OK)
			        .body(objQuestionResponse);
		}
		 catch(Exception e) {
				objErrorDTO.setErrorCode("333");
				objErrorDTO.setErrorMsg(e.getMessage());
				objQuestionResponse.setObjErrorDTO(objErrorDTO);
				return ResponseEntity.status(HttpStatus.OK)
				        .body(objQuestionResponse);
		 }
	}
	
	
}
     