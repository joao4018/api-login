package com.login.apilogin.domain.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author joaocarlos
 */
@Entity(name = "API_ACCESS_EMAIL")
@Getter
@Setter
@RequiredArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccessEmail {

    @Id
    @NotNull
    private String email;

    @NotNull
    private Long count;

    @CreationTimestamp
    @NotNull
    private LocalDateTime expiryDate = LocalDateTime.now();

    @CreationTimestamp
    @NotNull
    private LocalDateTime registryDate;

    @NotNull
    private Boolean emailTest = Boolean.FALSE;

}
