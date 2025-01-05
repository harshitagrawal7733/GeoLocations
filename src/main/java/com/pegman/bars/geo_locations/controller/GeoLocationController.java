package com.pegman.bars.geo_locations.controller;

import com.pegman.bars.geo_locations.Entity.GeoLocation;
import com.pegman.bars.geo_locations.service.GeoLocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/geo-locations")
public class GeoLocationController {

    private final GeoLocationService geoLocationService;

    public GeoLocationController(GeoLocationService geoLocationService) {
        this.geoLocationService = geoLocationService;
    }

    @GetMapping("/nearby")
    public List<GeoLocation> findNearbyLocations(
            @RequestHeader("latitude") double lat,
            @RequestHeader("longitude") double lon,
            @RequestHeader("distance") String distance) {
        return geoLocationService.findLocationsWithinDistance(distance, lat, lon);
    }

    @GetMapping("/all")
    public List<GeoLocationResponse> getAllLocations(
            @RequestHeader(value = "page", defaultValue = "0") int page,
            @RequestHeader(value = "size", defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return geoLocationService.getAllLocations(pageRequest);
    }
    @PostMapping("/bulkAdd")
    public ResponseEntity<List<GeoLocation>> saveGeoLocations(@RequestBody List<GeoLocationRequest> geoLocationRequests) {
        List<GeoLocation> savedGeoLocations = geoLocationService.saveGeoLocations(geoLocationRequests);
        return ResponseEntity.ok(savedGeoLocations);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllGeoLocation() {
        geoLocationService.deleteAll();
        return ResponseEntity.ok("All geo-locations have been successfully deleted.");
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGeoLocation(@PathVariable String id) {
        geoLocationService.deleteByGeoLocationId(id);
    }

}
