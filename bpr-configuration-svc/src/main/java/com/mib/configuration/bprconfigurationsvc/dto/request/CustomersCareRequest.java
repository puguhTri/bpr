package com.mib.configuration.bprconfigurationsvc.dto.request;


import lombok.Data;

import java.io.Serializable;

@Data
public class CustomersCareRequest implements Serializable {

    private String code;
    private String name;
    private String description;
}
