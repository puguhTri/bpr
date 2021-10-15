package com.mib.biller.bprbillersvc.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JackOneBillerPaymentRequest {
    @JsonProperty(value = "no_pelanggan")
    private String noPel;
    private Integer type;
}
