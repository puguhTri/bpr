package com.mib.biller.bprbillersvc.entities;

import com.mib.biller.bprbillersvc.base.BaseEntity;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerInquiryResponse;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerPaymentResponse;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity
@Data
public class Transaction extends BaseEntity {

    private UUID code;
    private UUID customerId;
    private String type;
    private String idPelanggan;//id pelanggan || kode bayar etc

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private BillerInquiryResponse inquiry;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private BillerPaymentResponse payment;


}
