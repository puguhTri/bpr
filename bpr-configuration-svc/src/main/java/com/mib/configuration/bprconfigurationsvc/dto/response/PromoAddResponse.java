package com.mib.configuration.bprconfigurationsvc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromoAddResponse {

    private String code;
    private String name;
    private String description;
    private ImageResponse image;

}
