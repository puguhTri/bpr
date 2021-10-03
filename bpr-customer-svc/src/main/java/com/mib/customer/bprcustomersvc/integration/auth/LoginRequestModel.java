package com.mib.customer.bprcustomersvc.integration.auth;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestModel {
    private String email;
    private String password;
}
