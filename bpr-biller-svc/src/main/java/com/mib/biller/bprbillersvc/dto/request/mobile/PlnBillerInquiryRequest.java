package com.mib.biller.bprbillersvc.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PlnBillerInquiryRequest implements Serializable {

    @JsonProperty(value = "id_pelanggan")
    private String clientId;
    private String type;

}
