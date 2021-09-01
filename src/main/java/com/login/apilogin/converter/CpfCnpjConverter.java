package com.login.apilogin.converter;

import com.login.apilogin.enums.CpfCnpjEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CpfCnpjConverter implements AttributeConverter<CpfCnpjEnum, String> {

    @Override
    public String convertToDatabaseColumn(CpfCnpjEnum attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getType();
    }

    @Override
    public CpfCnpjEnum convertToEntityAttribute(String dbData) {
        return CpfCnpjEnum.parse(dbData);
    }

}
