package com.mib.customer.bprcustomersvc.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterRequest {

    @JsonProperty(value = "account_number")
    private String accountNumber;

    @JsonProperty(value = "mother_name")
    private String motherName;

    @JsonProperty(value = "phone_number")
    private String phoneNumber;
}

