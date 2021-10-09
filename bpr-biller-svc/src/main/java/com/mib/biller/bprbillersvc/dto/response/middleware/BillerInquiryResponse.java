package com.mib.biller.bprbillersvc.dto.response.middleware;

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


    private String stan; //number
    private String retrievalReferenceNumber; //string
    private String transmissionDatetime; //number
    private String amount;
    private String private48;
    private String productCode;
    private String responseCode;
    private String private59;
    private String private61;
    private String private63;
}
