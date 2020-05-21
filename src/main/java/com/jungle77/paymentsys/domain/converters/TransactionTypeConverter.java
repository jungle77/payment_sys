package com.jungle77.paymentsys.domain.converters;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.jungle77.paymentsys.domain.enums.TransactionType;

@Converter
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {
 
    @Override
    public String convertToDatabaseColumn(TransactionType TransactionType) {
        return TransactionType.getName();
    }
 
    @Override
    public TransactionType convertToEntityAttribute(String dbValue) {
        return TransactionType.fromName(dbValue);
    }
 
}