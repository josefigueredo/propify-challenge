package com.propify.challenge.model;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Property implements Serializable {

    private Integer id; // must be null for INSERT and not null for UPDATE

    private String createTime;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @NotNull(message = "Rent Price should not be null")
    @DecimalMin("0")
    @Digits(integer = 10, fraction = 2) // Integer is required, I had to assume
    private BigDecimal rentPrice; // must be greater than 0, 2 decimal places

    @NotNull(message = "Address should not be null")
    private Address address; // must not be null

    @NotNull(message = "Email should not be null")
    @Email(message = "Email should be valid", regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?") // I use this regexp in my email validations because the default gives false positives
    private String emailAddress; // must be a valid email address

    @NotNull(message = "Code should not be null")
    @Pattern(regexp = "^[A-Z0-9]{10}$") // ^[A-Z]{10}|[0-9]{10}$ needs to clarify the OR if it is a set or an exclusion
    private String code; // not null, only uppercase letters or numbers, 10 characters
}
