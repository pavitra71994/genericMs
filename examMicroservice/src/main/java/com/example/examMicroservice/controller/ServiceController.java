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

	@Override
	public ResponseEntity<CreateQuestionResponse> createQuestionLog(QuestionBean objQuestionBean) {
		// TODO Auto-generated method stub

		CreateQuestionResponse objCreateQuestionResponse = new CreateQuestionResponse();
		ErrorDTO objErrorDTO = new ErrorDTO();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Access", "ajkshdkj");
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
		ErrorDTO objErrorDTO = new ErrorDTO();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
		// responseHeaders.set("Access-Control-Allow-Headers", "Origin,
		// Content-Type,X-Requested-With, Accept, X-Auth-Token");
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
			}if (serviceType.equals("checkResult")) {
				objQuesListFinal =objExamService.fetchQuestionLog();
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
		 String uri = "https://secureroute-genericms.apps.ca-central-1.starter.openshift-online.com/common/v1/sendmail";
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
		SendMailResponse result = new SendMailResponse();
		InetAddress ip;
        String hostname;
        System.out.println("serverPassword >>>>> "+ serverPassword);

		if (value.equals("ragnar")) {
			HttpHeaders headers = new HttpHeaders();
			headers.set("rootuser", value);
			try {
				ip = InetAddress.getLocalHost();
	            hostname = ip.getHostName();
	            System.out.println("Your current IP address : " + ip);
	            System.out.println("Your current Hostname : " + hostname);
	            //final String uri =hostname+uri; 
	            System.out.println("uri >>>>>  : " + ip);
				
				StringBuilder msgBody = new StringBuilder("Correct -" + objResultBodyRequest.getCorrectAns() + "\n");
				msgBody.append("InCorrect - " + objResultBodyRequest.getIncorrectAns() + "\n");
				msgBody.append("UnAttempted - " + objResultBodyRequest.getUnattemptedQues() + "\n");
				msgBody.append("Result - " + objResultBodyRequest.getResult() + "\n");
				HttpEntity<SendMailRequest> entity = new HttpEntity<>(objResultBodyRequest.getObjSendMailRequest(),
						headers);
				RestTemplate restTemplate = new RestTemplate();
				objResultBodyRequest.getObjSendMailRequest().setMessageBody(msgBody.toString());
				result = restTemplate.postForObject(uri, entity, SendMailResponse.class);
				return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(result);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).headers(responseHeaders).body(result);

			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).body(result);
	}

}
