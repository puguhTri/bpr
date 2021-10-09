package com.mib.biller.bprbillersvc.dto.request.middleware;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillerPaymentRequest implements Serializable {

    private String stan;
    private String retrievalReferenceNumber;
    private String amount;
    private String transmissionDatetime;
    private String private48;
    private String private59;
    private String private63;
    private String productCode;
    private String descAccount;
    private String feeAccount;
    private String srcAccount;

}
