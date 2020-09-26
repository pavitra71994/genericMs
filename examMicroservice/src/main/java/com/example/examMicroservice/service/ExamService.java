package com.example.examMicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.examMicroservice.bean.QuestionBean;
import com.example.examMicroservice.bean.ResultBodyRequest;
import com.example.examMicroservice.bean.SendMailRequest;
import com.example.examMicroservice.bean.SendMailResponse;
import com.example.examMicroservice.dao.ServiceDAO;
import com.example.examMicroservice.exceptions.NewApplicationException;

@Service
public class ExamService {

	@Autowired
	ServiceDAO objServiceDAO;

	public void createQuestionLog(QuestionBean objQuestionBean) {
		objServiceDAO.save(objQuestionBean);
	}

	public List<QuestionBean> fetchQuestionLog() {
		// TODO Auto-generated method stub
		return objServiceDAO.findAll();
	}

	public SendMailResponse sendResultViaMail(ResultBodyRequest objResultBodyRequest, String value) throws NewApplicationException {
		// TODO Auto-generated method stub
		String uri = "https://secureroute-genericms.apps.ca-central-1.starter.openshift-online.com/common/v1/sendmail";
		//String uri = "http://localhost:8080/common/v1/sendmail";
		SendMailResponse result = new SendMailResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.set("rootuser", value);
		StringBuilder msgBody = new StringBuilder("Correct -" + objResultBodyRequest.getCorrectAns() + "\n");
		msgBody.append("InCorrect - " + objResultBodyRequest.getIncorrectAns() + "\n");
		msgBody.append("UnAttempted - " + objResultBodyRequest.getUnattemptedQues() + "\n");
		msgBody.append("Result - " + objResultBodyRequest.getResult() + "\n");
		HttpEntity<SendMailRequest> entity = new HttpEntity<>(objResultBodyRequest.getObjSendMailRequest(), headers);
		RestTemplate restTemplate = new RestTemplate();
		objResultBodyRequest.getObjSendMailRequest().setMessageBody(msgBody.toString());
		result = restTemplate.postForObject(uri, entity, SendMailResponse.class);
		return result;

	}
}
