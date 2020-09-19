package com.example.examMicroservice.bean;

public class SendMailRequest {
	private String toEmail;
	private String fromMail;
	private String messageBody;
	private String subjectLine;

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getSubjectLine() {
		return subjectLine;
	}

	public void setSubjectLine(String subjectLine) {
		this.subjectLine = subjectLine;
	}

	public SendMailRequest() {
		super();
	}

	public SendMailRequest(String toEmail, String fromMail, String messageBody, String subjectLine) {
		super();
		this.toEmail = toEmail;
		this.fromMail = fromMail;
		this.messageBody = messageBody;
		this.subjectLine = subjectLine;
	}

}
