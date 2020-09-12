package com.example.examMicroservice.bean;

public class CreateQuestionResponse {
	private QuestionBean objQuestionBean;
	private ErrorDTO objErrorDTO;

	public QuestionBean getObjQuestionBean() {
		return objQuestionBean;
	}

	public void setObjQuestionBean(QuestionBean objQuestionBean) {
		this.objQuestionBean = objQuestionBean;
	}

	public ErrorDTO getObjErrorDTO() {
		return objErrorDTO;
	}

	public void setObjErrorDTO(ErrorDTO objErrorDTO) {
		this.objErrorDTO = objErrorDTO;
	}
	
	

}
