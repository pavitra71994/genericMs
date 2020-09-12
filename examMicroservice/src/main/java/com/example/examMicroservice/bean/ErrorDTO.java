package com.example.examMicroservice.bean;

public class ErrorDTO {
private String errorCode;
private String errorMsg;
public String getErrorCode() {
	return errorCode;
}
public void setErrorCode(String errorCode) {
	this.errorCode = errorCode;
}
public String getErrorMsg() {
	return errorMsg;
}
public void setErrorMsg(String errorMsg) {
	this.errorMsg = errorMsg;
}
public ErrorDTO(String errorCode, String errorMsg) {
	super();
	this.errorCode = errorCode;
	this.errorMsg = errorMsg;
}
public ErrorDTO() {
	super();
}

}
