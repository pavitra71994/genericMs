package com.example.examMicroservice.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AnswerBean {

	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer ansId;
	public String ans;
	public boolean isAnsTrue;
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public boolean isAnsTrue() {
		return isAnsTrue;
	}
	public void setAnsTrue(boolean isAnsTrue) {
		this.isAnsTrue = isAnsTrue;
	}
	public Integer getAnsId() {
		return ansId;
	}
	public void setAnsId(Integer ansId) {
		this.ansId = ansId;
	}
	public AnswerBean(Integer ansId, String ans, boolean isAnsTrue) {
		super();
		this.ansId = ansId;
		this.ans = ans;
		this.isAnsTrue = isAnsTrue;
	}
	public AnswerBean() {
		super();
	}
	
	
	
}
