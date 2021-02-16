package com.propify.challenge.model;

import lombok.Data;

import java.util.Map;

@Data
public class PropertyReport {

    private Integer totalQuantity;

    private Map<PropertyType, Integer> quantityPerType;

    private double averageRentPrice;

    private Integer illinoisQuantity;
}
