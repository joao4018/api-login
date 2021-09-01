package com.login.apilogin.enums;

import lombok.Getter;

public enum CpfCnpjEnum {

    CPF("CPF"),
    CNPJ("CNPJ");

    @Getter
    private final String type;

    CpfCnpjEnum(String type) {
        this.type = type;
    }

    public static CpfCnpjEnum parse(String type) {
        if (type == null) {
            return null;
        }
        for (CpfCnpjEnum e : CpfCnpjEnum.values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        throw new AssertionError(type);
    }

}
