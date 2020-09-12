package com.example.examMicroservice.bean;

public class ResultBean {

	private int answeredQues;
	private int unAnsweredQues;
	private int incorectQues;
	private String passFailFlag;
	public int getAnsweredQues() {
		return answeredQues;
	}
	public void setAnsweredQues(int answeredQues) {
		this.answeredQues = answeredQues;
	}
	public int getUnAnsweredQues() {
		return unAnsweredQues;
	}
	public void setUnAnsweredQues(int unAnsweredQues) {
		this.unAnsweredQues = unAnsweredQues;
	}
	public int getIncorectQues() {
		return incorectQues;
	}
	public void setIncorectQues(int incorectQues) {
		this.incorectQues = incorectQues;
	}
	public String getPassFailFlag() {
		return passFailFlag;
	}
	public void setPassFailFlag(String passFailFlag) {
		this.passFailFlag = passFailFlag;
	}
	public ResultBean(int answeredQues, int unAnsweredQues, int incorectQues, String passFailFlag) {
		super();
		this.answeredQues = answeredQues;
		this.unAnsweredQues = unAnsweredQues;
		this.incorectQues = incorectQues;
		this.passFailFlag = passFailFlag;
	}
	public ResultBean() {
		super();
	}
	
	
}
