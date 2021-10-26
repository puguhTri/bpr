package com.mib.configuration.bprconfigurationsvc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromoListResponse {
    private String code;
    private String name;
    private String description;
    private String fileId;
}
