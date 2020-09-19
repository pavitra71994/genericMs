package com.example.examMicroservice.bean;

public class ResultBodyRequest {
	private int correctAns;
	private int incorrectAns;
	private int unattemptedQues;
	private String result;
	private SendMailRequest objSendMailRequest;
	public int getCorrectAns() {
		return correctAns;
	}
	public void setCorrectAns(int correctAns) {
		this.correctAns = correctAns;
	}
	public int getIncorrectAns() {
		return incorrectAns;
	}
	public void setIncorrectAns(int incorrectAns) {
		this.incorrectAns = incorrectAns;
	}
	public int getUnattemptedQues() {
		return unattemptedQues;
	}
	public void setUnattemptedQues(int unattemptedQues) {
		this.unattemptedQues = unattemptedQues;
	}
	
	public ResultBodyRequest(int correctAns, int incorrectAns, int unattemptedQues, String result) {
		super();
		this.correctAns = correctAns;
		this.incorrectAns = incorrectAns;
		this.unattemptedQues = unattemptedQues;
		result = result;
	}
	public ResultBodyRequest() {
		super();
	}
	public SendMailRequest getObjSendMailRequest() {
		return objSendMailRequest;
	}
	public void setObjSendMailRequest(SendMailRequest objSendMailRequest) {
		this.objSendMailRequest = objSendMailRequest;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	

}
