package com.mib.customer.bprcustomersvc.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckMpinResponse {

    @JsonProperty(value = "customer_id")
    private UUID customerId;
    private Boolean mpinValidated;

    private String name;
    private Boolean status;
}
