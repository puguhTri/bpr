package com.mib.biller.bprbillersvc.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PlnBillerInquiryRequest implements Serializable {

    @JsonProperty(value = "no_pelanggan")
    private String noPel;
    private Integer type;

}
