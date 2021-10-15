package com.mib.biller.bprbillersvc.base;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @UpdateTimestamp
    @Column(insertable = false)
    private Date modifiedDate = new Date();
    @CreationTimestamp
    private Date createdDate = new Date();
}
