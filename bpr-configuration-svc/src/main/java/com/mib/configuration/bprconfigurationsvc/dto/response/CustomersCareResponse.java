package com.mib.configuration.bprconfigurationsvc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomersCareResponse {

    private UUID uuid;
    private String code;
    private String name;
    private String description;
}
