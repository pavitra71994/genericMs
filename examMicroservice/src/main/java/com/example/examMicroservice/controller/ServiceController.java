package com.example.examMicroservice.controller;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.examMicroservice.bean.AnswerBean;
import com.example.examMicroservice.bean.CreateQuestionResponse;
import com.example.examMicroservice.bean.ErrorDTO;
import com.example.examMicroservice.bean.QuestionBean;
import com.example.examMicroservice.bean.QuestionResponse;
import com.example.examMicroservice.bean.ResultBodyRequest;
import com.example.examMicroservice.bean.SendMailRequest;
import com.example.examMicroservice.bean.SendMailResponse;
import com.example.examMicroservice.service.ExamService;
import com.example.examMicroservice.serviceInterface.ServiceInterface;

@Component
public class ServiceController implements ServiceInterface {

	@Autowired
	ExamService objExamService;

	@Value("${spring.mail.password}")
	String serverPassword;

	@Autowired
	ErrorDTO objErrorDTO;

	@Autowired
	HttpHeaders responseHeaders;

	@Value("${headerValue}")
	String headerValue;

	@Override
	public ResponseEntity<CreateQuestionResponse> createQuestionLog(QuestionBean objQuestionBean) {
		// TODO Auto-generated method stub

		CreateQuestionResponse objCreateQuestionResponse = new CreateQuestionResponse();
		try {
			objExamService.createQuestionLog(objQuestionBean);
			objCreateQuestionResponse.setObjQuestionBean(objQuestionBean);

			objErrorDTO.setErrorCode("200");
			objErrorDTO.setErrorMsg("Success");
			objCreateQuestionResponse.setObjErrorDTO(objErrorDTO);
			return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(objCreateQuestionResponse);
		} catch (Exception e) {
			objCreateQuestionResponse.setObjQuestionBean(objQuestionBean);
			objErrorDTO.setErrorCode("333");
			objErrorDTO.setErrorMsg(e.getMessage());
			objCreateQuestionResponse.setObjErrorDTO(objErrorDTO);
			return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(objCreateQuestionResponse);
		}

	}

	@Override
	public ResponseEntity<QuestionResponse> fecthQuestionLog(String serviceType) {
		// TODO Auto-generated method stub

		QuestionResponse objQuestionResponse = new QuestionResponse();
		try {
			List<QuestionBean> objQuesListFinal = new ArrayList<QuestionBean>();

			if (serviceType.equals("showQuestion")) {
				List<QuestionBean> objQuesList = new ArrayList<QuestionBean>();
				objQuesList = objExamService.fetchQuestionLog();
				Iterator<QuestionBean> quesIt = objQuesList.iterator();
				while (quesIt.hasNext()) {
					QuestionBean quesObj = quesIt.next();
					Iterator<AnswerBean> ansIt = quesObj.getAnsList().iterator();
					while (ansIt.hasNext()) {
						AnswerBean ansObj = ansIt.next();
						ansObj.setAnsTrue(false);
					}
				}
				objQuesListFinal = objQuesList;
			}
			if (serviceType.equals("checkResult")) {
				objQuesListFinal = objExamService.fetchQuestionLog();
			}

			objErrorDTO.setErrorCode("200");
			objErrorDTO.setErrorMsg("Success");
			objQuestionResponse.setObjErrorDTO(objErrorDTO);
			objQuestionResponse.setQuestionAns(objQuesListFinal);
			return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(objQuestionResponse);
		} catch (Exception e) {
			objErrorDTO.setErrorCode("333");
			objErrorDTO.setErrorMsg(e.getMessage());
			objQuestionResponse.setObjErrorDTO(objErrorDTO);
			return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(objQuestionResponse);
		}
	}

	@Override
	public ResponseEntity<SendMailResponse> sendResultViaMail(ResultBodyRequest objResultBodyRequest, String value) {
		// TODO Auto-generated method stub
		SendMailResponse result = new SendMailResponse();
		if (value.equals("ragnar")) {
			try {
				result = objExamService.sendResultViaMail(objResultBodyRequest,value);
				return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(result);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).headers(responseHeaders).body(result);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).body(result);
	}

}
