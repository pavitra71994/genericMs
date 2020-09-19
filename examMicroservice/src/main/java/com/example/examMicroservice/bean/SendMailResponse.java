package com.example.examMicroservice.bean;

public class SendMailResponse {
	private SendMailRequest objSendMailRequest;
	private ErrorDTO objErrorDTO;

	public SendMailRequest getObjSendMailRequest() {
		return objSendMailRequest;
	}

	public void setObjSendMailRequest(SendMailRequest objSendMailRequest) {
		this.objSendMailRequest = objSendMailRequest;
	}

	public ErrorDTO getObjErrorDTO() {
		return objErrorDTO;
	}

	public void setObjErrorDTO(ErrorDTO objErrorDTO) {
		this.objErrorDTO = objErrorDTO;
	}

	public SendMailResponse() {
		super();
	}

	public SendMailResponse(SendMailRequest objSendMailRequest, ErrorDTO objErrorDTO) {
		super();
		this.objSendMailRequest = objSendMailRequest;
		this.objErrorDTO = objErrorDTO;
	}

}
