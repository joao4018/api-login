package com.login.apilogin.domain.impl;

import com.login.apilogin.converter.CpfCnpjConverter;
import com.login.apilogin.enums.CpfCnpjEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Entity(name = "API_PERSONAL_DATA")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The user firstName cannot be empty")
    @NotNull
    @Column(unique = true)
    private String cpfCpnj;

    @NotNull
    @Convert(converter = CpfCnpjConverter.class)
    private CpfCnpjEnum identityTipe;

    @NotEmpty(message = "The user firstName cannot be empty")
    @NotNull
    private String firstName;

    @NotEmpty(message = "The lastName cannot be empty")
    @NotNull
    private String lastName;

    @NotNull
    private Date bithDate;

}

