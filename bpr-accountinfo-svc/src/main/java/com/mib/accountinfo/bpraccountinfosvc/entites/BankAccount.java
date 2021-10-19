package com.mib.accountinfo.bpraccountinfosvc.entites;

import com.mib.accountinfo.bpraccountinfosvc.base.BaseEntity;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class BankAccount extends BaseEntity {

    private UUID uuid;
    private String rekening;
    private String status;
    private String name;
}
