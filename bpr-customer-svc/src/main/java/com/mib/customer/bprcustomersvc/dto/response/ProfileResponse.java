package com.mib.customer.bprcustomersvc.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {

    private String name;
    private String email;
    @JsonProperty(value = "account_number")
    private String accountNumber;
}
