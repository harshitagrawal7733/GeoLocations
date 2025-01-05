package com.pegman.bars.geo_locations.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.annotations.Document;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.geo.Point;

@Data
@Document(indexName = "bars_geo_locations")  // OpenSearch index name
public class GeoLocation {

    @Id
    private String id;

    private String name;

    private String address;

    @GeoPointField
    private Point location;

    /*x represents longitude (lon).
    y represents latitude (lat).*/

    /*Google Maps gives latitude first and longitude second. This is the opposite of the GeoJSON format, which expects longitude first and latitude second.

    Example from Google Maps:
    Coordinates:
            22.552173751658874, 88.35320905039474

    Here:

            22.552173751658874 → Latitude (north-south position).
            88.35320905039474 → Longitude (east-west position).

            22.554130251514238, 88.35162970677196

            22.545240913531003, 88.34195867680458

            22.624409424711104, 88.4444063088246
     */


}
