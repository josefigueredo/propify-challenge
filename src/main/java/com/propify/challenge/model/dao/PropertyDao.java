package com.propify.challenge.model.dao;

import com.propify.challenge.model.dto.PropertyType;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "Property")
@Table("property")
public class PropertyDao implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.annotation.Id
    private Integer id;

    private LocalDateTime createTime;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    private BigDecimal rentPrice;

    private Integer addressId;

    private String emailAddress;

    private String code;
}
