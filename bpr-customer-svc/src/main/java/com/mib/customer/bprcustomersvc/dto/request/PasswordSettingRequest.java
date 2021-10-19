package com.mib.customer.bprcustomersvc.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class PasswordSettingRequest {

    @JsonProperty(value = "customer_id")
    private UUID customerId;
    private String name;
    private String password;
    @JsonProperty(value = "password_confirm")
    private String passwordConfirm;
    private String email;
    private String username;
}
