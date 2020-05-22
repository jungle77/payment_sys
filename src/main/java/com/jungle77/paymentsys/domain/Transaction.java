package com.jungle77.paymentsys.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.jungle77.paymentsys.domain.converters.TransactionStatusConverter;
import com.jungle77.paymentsys.domain.converters.TransactionTypeConverter;
import com.jungle77.paymentsys.domain.enums.TransactionStatus;
import com.jungle77.paymentsys.domain.enums.TransactionType;

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

    @ManyToOne(targetEntity = Transaction.class)
    @JoinColumn(name = "parent_transaction")
    private Transaction parentTransaction;
    
    @ManyToOne(targetEntity = Merchand.class)
    @JoinColumn(name = "merchand_id")
    private Merchand merchand;
    
    @NotNull
    @Column(name = "type")
    @Convert(converter = TransactionTypeConverter.class)
    private TransactionType type;
    
    @Column(name = "status")
    @Convert(converter = TransactionStatusConverter.class)
    private TransactionStatus status;
    
    @Column(name = "amount")
    private BigDecimal amount;
    
    @Column(name = "customer_email")
    private String customerEmail;
    
    @Column(name = "customer_phone")
    private String customerPhone;
    
    @Column(name = "reference_id")
    private String referenceId;
    
    @Column(name = "created_at", updatable = false)
    private Timestamp createDate;
    
    @Column(name = "updated_at", updatable = false)
    private Timestamp updateDate;
    
}