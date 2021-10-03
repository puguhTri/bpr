package com.mib.customer.bprcustomersvc.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LoginResponse {

    private String name;
    @JsonProperty(value = "customer_id")
    private UUID customerId;
    @JsonProperty(value = "access_token")
    private String accessToken;
    @JsonProperty(value = "expire_in")
    private String expireIn;
}
