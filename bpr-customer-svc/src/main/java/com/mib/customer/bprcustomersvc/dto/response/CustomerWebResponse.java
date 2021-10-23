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
public class CustomerWebResponse {

    private UUID customerId;
    private String name;
    private String email;
    @JsonProperty(value = "account_number")
    private String accountNumber;

    private Boolean status;
}
