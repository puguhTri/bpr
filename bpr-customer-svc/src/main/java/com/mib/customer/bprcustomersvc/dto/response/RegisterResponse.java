package com.mib.customer.bprcustomersvc.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {

    private String email;
    private String phoneNumber;
    private String otp;
}
