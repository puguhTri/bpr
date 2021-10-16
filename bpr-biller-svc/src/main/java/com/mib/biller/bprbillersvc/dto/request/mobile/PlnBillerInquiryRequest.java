package com.mib.biller.bprbillersvc.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PlnBillerInquiryRequest implements Serializable {

    @JsonProperty(value = "id_pelanggan")
    @NotNull
    private String clientId;
    private String type;

}
