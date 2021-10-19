package com.mib.auth.bprauthsvc.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = -2274490587246257518L;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userId;
    private String username;


}
