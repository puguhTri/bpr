package com.mib.biller.bprbillersvc.dto.request.middleware;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
public class BillerPlnRequest implements Serializable {

    @Length(min = 6, max = 6)
    private String stan; //number
    @Length(min = 12, max = 12)
    private String retrievalReferenceNumber; //string
    @Length(min = 10, max = 10)
    private String transmissionDatetime; //number
    @Length(min = 12, max = 12)
    private String amount;
    @Length(max = 999)
    private String private48;
    private String productCode;

}
