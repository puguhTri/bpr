package com.mib.customer.bprcustomersvc.integration.auth;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestModel {
    private String email;
    private String password;
}
