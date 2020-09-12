package com.example.examMicroservice.bean;

import java.util.List;

public class QuestionResponse {
	private List<QuestionBean> questionAns;
	private ErrorDTO objErrorDTO;
	public List<QuestionBean> getQuestionAns() {
		return questionAns;
	}
	public void setQuestionAns(List<QuestionBean> questionAns) {
		this.questionAns = questionAns;
	}
	public ErrorDTO getObjErrorDTO() {
		return objErrorDTO;
	}
	public void setObjErrorDTO(ErrorDTO objErrorDTO) {
		this.objErrorDTO = objErrorDTO;
	}
	public QuestionResponse(List<QuestionBean> questionAns, ErrorDTO objErrorDTO) {
		super();
		this.questionAns = questionAns;
		this.objErrorDTO = objErrorDTO;
	}
	public QuestionResponse() {
		super();
	}
	
	
	
	

}
