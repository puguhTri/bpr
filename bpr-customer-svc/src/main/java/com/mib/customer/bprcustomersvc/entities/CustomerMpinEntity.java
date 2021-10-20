package com.mib.customer.bprcustomersvc.entities;

import com.mib.customer.bprcustomersvc.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "customer_mpin")
public class CustomerMpinEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    private String pin;
    private String pinEncryption;
    private Integer counter;
}
