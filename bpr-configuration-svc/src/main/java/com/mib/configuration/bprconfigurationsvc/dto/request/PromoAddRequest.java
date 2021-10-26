package com.mib.configuration.bprconfigurationsvc.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PromoAddRequest {

    private String code;
    private String name;
    private String description;
    @JsonProperty(value = "file_id")
    private String fileId;

}
