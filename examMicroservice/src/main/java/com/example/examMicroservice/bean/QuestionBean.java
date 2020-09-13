package com.example.examMicroservice.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class QuestionBean {
	
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE )
	private int quesNo;
	private String ques;
	@OneToMany(targetEntity=AnswerBean.class,cascade=CascadeType.ALL)
	private List<AnswerBean> ansList;
	public int getQuesNo() {
		return quesNo;
	}
	public void setQuesNo(int quesNo) {
		this.quesNo = quesNo;
	}
	public String getQues() {
		return ques;
	}
	public void setQues(String ques) {
		this.ques = ques;
	}
	
	public List<AnswerBean> getAnsList() {
		return ansList;
	}
	public void setAnsList(List<AnswerBean> ansList) {
		this.ansList = ansList;
	}
	public QuestionBean(int quesNo, String ques, List<AnswerBean> ansList) {
		super();
		this.quesNo = quesNo;
		this.ques = ques;
		this.ansList = ansList;
	}
	public QuestionBean() {
		super();
	}
	
	

}
