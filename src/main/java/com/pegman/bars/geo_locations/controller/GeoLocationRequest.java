package com.pegman.bars.geo_locations.controller;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;


@Data
public class GeoLocationRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lon")
    private double lon;

}
