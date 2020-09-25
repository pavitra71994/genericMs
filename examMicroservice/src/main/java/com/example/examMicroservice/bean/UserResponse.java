package com.example.examMicroservice.bean;

import java.util.ArrayList;

public class UserResponse {
	private ArrayList<UserBean> objUser;
	private ErrorDTO objErrorDTO;
	
	public ArrayList<UserBean> getObjUser() {
		return objUser;
	}
	public void setObjUser(ArrayList<UserBean> objUser) {
		this.objUser = objUser;
	}
	public ErrorDTO getObjErrorDTO() {
		return objErrorDTO;
	}
	public void setObjErrorDTO(ErrorDTO objErrorDTO) {
		this.objErrorDTO = objErrorDTO;
	}
	public UserResponse() {
		super();
	}
	
	
}
