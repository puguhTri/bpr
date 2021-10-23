package com.mib.configuration.bprconfigurationsvc.entities;

import com.mib.configuration.bprconfigurationsvc.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "layanan_pelanggan")
public class CustomersCareEntity extends BaseEntity {

    private UUID uuid;
    private String code;
    private String name;
    private String description;

    private Boolean status;

}
