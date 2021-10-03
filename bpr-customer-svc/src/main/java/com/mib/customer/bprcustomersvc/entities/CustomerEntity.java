package com.mib.customer.bprcustomersvc.entities;


import com.mib.customer.bprcustomersvc.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {

    private UUID customerId;
    private UUID userId;
    private String name;
    private String accountNumber;
    private String email;
    private String phoneNumber;
    private String identityNumber;

}
