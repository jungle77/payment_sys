package com.jungle77.paymentsys.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @NotNull
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "parent_transaction")
    private String parentTransaction;
    
    @NotNull
    @Column(name = "merchand_id")
    private String merchandId;
    
    @Column(name = "type")
    private Integer type;
    
    @Column(name = "status")
    private Integer status;
    
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;
    
    @Column(name = "customer_email")
    private String customerEmail;
    
    @Column(name = "customer_phone")
    private String customerPhone;
    
    @Column(name = "reference_id")
    private String referenceId;
    
}