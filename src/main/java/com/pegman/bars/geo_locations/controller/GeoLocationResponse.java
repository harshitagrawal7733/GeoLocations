package com.pegman.bars.geo_locations.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class GeoLocationResponse {
    private String name;

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lon")
    private double longitude;

    private String address;


}
