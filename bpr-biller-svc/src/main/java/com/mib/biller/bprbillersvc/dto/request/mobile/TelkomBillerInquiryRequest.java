package com.mib.biller.bprbillersvc.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TelkomBillerInquiryRequest {

    @JsonProperty(value = "kode_area")
    @NotNull
    private String area;
    @JsonProperty(value = "id_pelanggan")
    @NotNull
    private String clientId;
    private String type;
}
