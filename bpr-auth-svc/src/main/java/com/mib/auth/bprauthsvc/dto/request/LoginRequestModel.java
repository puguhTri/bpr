package com.mib.auth.bprauthsvc.dto.request;

import lombok.Data;

@Data
public class LoginRequestModel {

	private String username;
	private String email;
	private String password;


}
