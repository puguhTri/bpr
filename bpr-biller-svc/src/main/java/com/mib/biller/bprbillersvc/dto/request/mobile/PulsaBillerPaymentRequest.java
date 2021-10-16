package com.mib.biller.bprbillersvc.dto.request.mobile;

import lombok.Data;

import java.util.UUID;

@Data
public class PulsaBillerPaymentRequest {
    private UUID code;
}
