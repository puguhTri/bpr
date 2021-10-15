package com.mib.biller.bprbillersvc.dto.response.mobile;

import com.mib.biller.bprbillersvc.dto.response.middleware.BillerInquiryResponse;
import lombok.Data;

import java.util.UUID;

@Data
public class BillerInquiryResponseMB {

    private BillerInquiryResponse inquiry;
    private UUID code;

}
