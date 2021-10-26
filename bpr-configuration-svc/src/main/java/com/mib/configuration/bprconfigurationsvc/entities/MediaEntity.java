package com.mib.configuration.bprconfigurationsvc.entities;

import com.mib.configuration.bprconfigurationsvc.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "media")
public class MediaEntity extends BaseEntity {
    private String fileId;
    private String url;
    private String fileName;
}
