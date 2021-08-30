package com.login.apilogin.enums;

import lombok.Getter;

public enum UserRoleEnum {
    ROLE_USER("ROLE_USER", "USER"),
    ROLE_ADMIN("ROLE_ADMIN", "ADMIN"),
    ROLE_CLIENT("ROLE_CLIENT", "CLIENT"),
    ROLE_MANAGER("ROLE_MANAGER", "MANAGER"),
    ROLE_GUESS("ROLE_GUESS", "GUESS");

    private final String role;
    @Getter
    private final String type;

    UserRoleEnum(String role, String type) {

        this.role = role;
        this.type = type;
    }

    public static UserRoleEnum parse(String type) {
        if (type == null) {
            return null;
        }
        for (UserRoleEnum e : UserRoleEnum.values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        throw new AssertionError(type);
    }

}
