package com.propify.challenge.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class Address implements Serializable {

    private Integer id; // This is inferred by the AddressMapper class

    @NotNull
    @NotBlank
    private String street; // must not be null or blank

    @NotNull
    @NotBlank
    private String city; // must not be null or blank

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$")
    private String state; // 2-letter code, must not be null or blank

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]{5}$")
    private String zip; // 5-digit code, must not be null or blank

    @NotNull
    @NotBlank
    private String timezone; // Must be a valid timezone
}
