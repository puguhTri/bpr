package com.mib.customer.bprcustomersvc.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    @JsonProperty(value = "account_number")
    private String accountNumber;
    private String email;
    @JsonProperty(value = "phone_number")
    private String phoneNumber;
    @JsonProperty(value = "identity_number")
    private String identityNumber;
}
