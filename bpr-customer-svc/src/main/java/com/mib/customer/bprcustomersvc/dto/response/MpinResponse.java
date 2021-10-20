package com.mib.customer.bprcustomersvc.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
public class MpinResponse implements Serializable {

    @JsonProperty(value = "customer_id")
    private UUID customerId;
}
