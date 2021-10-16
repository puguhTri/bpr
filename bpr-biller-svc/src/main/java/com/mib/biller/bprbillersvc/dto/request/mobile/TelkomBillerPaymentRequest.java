package com.mib.biller.bprbillersvc.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class TelkomBillerPaymentRequest {
    private UUID code;
}
