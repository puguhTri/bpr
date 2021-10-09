package com.mib.biller.bprbillersvc.dto.response.middleware;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillerInquiryResponse implements Serializable {

    @JsonProperty(value = "STAN")
    private String stan; //number

    @JsonProperty(value = "RETRIEVAL_REFERENCE_NUMBER")
    private String retrievalReferenceNumber; //string

    @JsonProperty(value = "TRANSMISSION_DATETIME")
    private String transmissionDatetime; //number

    @JsonProperty(value = "AMOUNT")
    private String amount;

    @JsonProperty(value = "PRIVATE_48")
    private String private48;

    @JsonProperty(value = "PRODUCT_CODE")
    private String productCode;

    @JsonProperty(value = "RESPONSE_CODE")
    private String responseCode;

    @JsonProperty(value = "PRIVATE_59")
    private String private59;

    @JsonProperty(value = "PRIVATE_61")
    private String private61;

    @JsonProperty(value = "PRIVATE_63")
    private String private63;

    @JsonProperty(value = "DEST_ACCOUNT")
    private String destAccount;

    @JsonProperty(value = "FEE_ACCOUNT")
    private String fessAccount;
}