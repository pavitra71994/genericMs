package com.example.examMicroservice.bean;

public class RegisterUserResponse {
	private UserBean objUser;
	private ErrorDTO objErrorDTO;
	public UserBean getObjUser() {
		return objUser;
	}
	public void setObjUser(UserBean objUser) {
		this.objUser = objUser;
	}
	public ErrorDTO getObjErrorDTO() {
		return objErrorDTO;
	}
	public void setObjErrorDTO(ErrorDTO objErrorDTO) {
		this.objErrorDTO = objErrorDTO;
	}
}
