package com.mib.biller.bprbillersvc.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class KaiBillerInquiryRequest {
    @JsonProperty(value = "kode_pembayaran")
    @NotNull
    private String payCode;
    private String type;
}
