package com.login.apilogin.apiParameters;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static org.hibernate.type.IntegerType.ZERO;

@NoArgsConstructor
public class SystemParameters {
    public static final Integer PAGE_SIZE = BigDecimal.valueOf(5L).intValue();
    public static final Integer PAGE_INIT = ZERO;

}
