package com.mib.configuration.bprconfigurationsvc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromoFileResponse {

    private String fileId;
    private String url;
}
