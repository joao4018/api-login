package com.login.apilogin.domain.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "API_ACCESS_CODE")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccessCode {

    @Id
    @NotNull
    private String email;

    @NotEmpty(message = "Code of access")
    @NotNull
    private String code;

    @CreationTimestamp
    @NotNull
    private LocalDateTime expiryDate;

    @CreationTimestamp
    @NotNull
    private LocalDateTime registryDate;

}
