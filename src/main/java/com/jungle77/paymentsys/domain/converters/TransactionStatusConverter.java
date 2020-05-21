package com.jungle77.paymentsys.domain.converters;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.jungle77.paymentsys.domain.enums.TransactionStatus;

@Converter
public class TransactionStatusConverter implements AttributeConverter<TransactionStatus, String> {
 
    @Override
    public String convertToDatabaseColumn(TransactionStatus TransactionStatus) {
        return TransactionStatus.getName();
    }
 
    @Override
    public TransactionStatus convertToEntityAttribute(String dbValue) {
        return TransactionStatus.fromName(dbValue);
    }
 
}
