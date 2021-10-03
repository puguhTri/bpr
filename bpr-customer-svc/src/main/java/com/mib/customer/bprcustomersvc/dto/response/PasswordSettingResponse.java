package com.mib.customer.bprcustomersvc.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PasswordSettingResponse {

    @JsonProperty(value = "customer_id")
    private UUID customerId;
    private String name;
    private String email;

}
