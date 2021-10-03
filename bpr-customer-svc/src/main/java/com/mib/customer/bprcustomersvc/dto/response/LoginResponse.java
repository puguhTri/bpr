package com.mib.customer.bprcustomersvc.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponse {

    private String name;
    @JsonProperty(value = "customer_id")
    private String customerId;
    @JsonProperty(value = "access_token")
    private String accessToken;
    @JsonProperty(value = "expire_in")
    private String expireIn;
}
