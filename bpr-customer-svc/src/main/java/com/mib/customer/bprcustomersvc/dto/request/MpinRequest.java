package com.mib.customer.bprcustomersvc.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.UUID;

@Data
public class MpinRequest implements Serializable {

    @JsonProperty(value = "customer_id")
    private UUID customerId;
    @Min(6)
    @Max(6)
    private Integer mpin;

    @Min(6)
    @Max(6)
    @JsonProperty(value = "mpin_confirm")
    private Integer mpinConfirm;

}
