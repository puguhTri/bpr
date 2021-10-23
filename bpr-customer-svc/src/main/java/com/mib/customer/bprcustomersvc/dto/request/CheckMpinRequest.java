package com.mib.customer.bprcustomersvc.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class CheckMpinRequest {

    @JsonProperty(value = "customer_id")
    private UUID customerId;
    private String mpin;
}
