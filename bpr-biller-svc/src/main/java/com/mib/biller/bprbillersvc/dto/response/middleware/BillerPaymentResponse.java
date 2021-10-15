package com.mib.biller.bprbillersvc.dto.response.middleware;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillerPaymentResponse {

    @JsonProperty(value = "STAN")
    private String STAN;

    @JsonProperty(value = "RETRIEVAL_REFERENCE_NUMBER")
    private String RETRIEVAL_REFERENCE_NUMBER;

    @JsonProperty(value = "AMOUNT")
    private String AMOUNT;

    @JsonProperty(value = "TRANSMISSION_DATETIME")
    private String TRANSMISSION_DATETIME;

    @JsonProperty(value = "PRIVATE_48")
    private String PRIVATE_48;

    @JsonProperty(value = "PRIVATE_59")
    private String PRIVATE_59;

    @JsonProperty(value = "PRIVATE_63")
    private String PRIVATE_63;

    @JsonProperty(value = "PRODUCT_CODE")
    private String PRODUCT_CODE;

    @JsonProperty(value = "DEST_ACCOUNT")
    private String DEST_ACCOUNT;

    @JsonProperty(value = "FEE_ACCOUNT")
    private String FEE_ACCOUNT;

    @JsonProperty(value = "SRC_ACCOUNT")
    private String SRC_ACCOUNT;

    @JsonProperty(value = "RESPONSE_CODE")
    private String RESPONSE_CODE;

    @JsonProperty(value = "PRIVATE_61")
    private String PRIVATE_61;


}
