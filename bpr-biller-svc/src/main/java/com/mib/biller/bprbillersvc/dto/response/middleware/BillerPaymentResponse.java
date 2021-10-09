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
    private String stan;

    @JsonProperty(value = "RETRIEVAL_REFERENCE_NUMBER")
    private String retrievalReferenceNumber;

    @JsonProperty(value = "AMOUNT")
    private String amount;

    @JsonProperty(value = "TRANSMISSION_DATETIME")
    private String transmissionDatetime;

    @JsonProperty(value = "PRIVATE_48")
    private String private48;

    @JsonProperty(value = "PRIVATE_59")
    private String private59;

    @JsonProperty(value = "PRIVATE_63")
    private String private63;

    @JsonProperty(value = "PRODUCT_CODE")
    private String productCode;

    @JsonProperty(value = "DEST_ACCOUNT")
    private String descAccount;

    @JsonProperty(value = "FEE_ACCOUNT")
    private String feeAccount;

    @JsonProperty(value = "SRC_ACCOUNT")
    private String srcAccount;

    @JsonProperty(value = "RESPONSE_CODE")
    private String responseCode;

    @JsonProperty(value = "PRIVATE_61")
    private String private61;


}
