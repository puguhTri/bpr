package com.mib.configuration.bprconfigurationsvc.entities;

import com.mib.configuration.bprconfigurationsvc.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "promo")
public class PromoEntity extends BaseEntity {

    private String code;
    private String name;
    private String description;
    private String fileId;
}
