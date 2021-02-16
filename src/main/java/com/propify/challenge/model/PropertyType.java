package com.propify.challenge.model;

public enum PropertyType {

    SINGLE_FAMILY("Single Family"),
    MULTI_FAMILY("Multi-family"),
    CONDOMINIUM("Condominium"),
    TOWNHOUSE("Townhouse");

    String type;

    PropertyType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
