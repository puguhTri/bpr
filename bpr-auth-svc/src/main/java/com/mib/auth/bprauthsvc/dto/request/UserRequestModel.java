package com.mib.auth.bprauthsvc.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequestModel {

	@NotNull(message = "First name cannot be null")
	@Size(min = 2)
	private String firstName;
	private String lastName;
	
	@NotNull
	@Size(min = 8, max = 16)
	private String password;
	
	@NotNull
	@Email
	private String email;



}
