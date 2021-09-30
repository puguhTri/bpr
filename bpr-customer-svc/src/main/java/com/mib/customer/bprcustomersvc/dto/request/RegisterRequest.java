package com.mib.customer.bprcustomersvc.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String accountNumber;
    private String email;
    private String phoneNumber;
    private String identityNumber;
}
