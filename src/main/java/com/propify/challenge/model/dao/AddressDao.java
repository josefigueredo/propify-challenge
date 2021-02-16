package com.propify.challenge.model.dao;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
@Entity
@Table("address")
public class AddressDao implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.annotation.Id
    private Integer id;

    private String street;

    private String city;

    private String state;

    private String zip;

    private String timezone;
}
