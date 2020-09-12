package com.example.examMicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examMicroservice.bean.QuestionBean;
import com.example.examMicroservice.dao.ServiceDAO;

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
}
