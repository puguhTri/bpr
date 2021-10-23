package com.mib.customer.bprcustomersvc.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailCustomerResponse {
    private String name;
    private String email;
    @JsonProperty(value = "account_number")
    private String accountNumber;

    private Boolean status;
}
