package com.mib.customer.bprcustomersvc.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String email;
    private String password;
}
