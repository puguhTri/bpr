package com.mib.customer.bprcustomersvc.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RegisterResponse {

    @JsonProperty(value = "customer_id")
    private UUID customerId;
    private String email;
    @JsonProperty(value = "phone_number")
    private String phoneNumber;
    private String otp;
}
