package com.pegman.bars.geo_locations.service;

import com.pegman.bars.geo_locations.Entity.GeoLocation;
import com.pegman.bars.geo_locations.controller.GeoLocationRequest;
import com.pegman.bars.geo_locations.controller.GeoLocationResponse;
import com.pegman.bars.geo_locations.repo.GeoLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GeoLocationService {

    private final GeoLocationRepository geoLocationRepository;

    @Autowired
    public GeoLocationService(GeoLocationRepository geoLocationRepository) {
        this.geoLocationRepository = geoLocationRepository;
    }

    public List<GeoLocation> findLocationsWithinDistance(String distance, double lat, double lon) {
        return geoLocationRepository.findByLocationWithin(distance,lat,lon);
    }
    public GeoLocation convertToEntity(GeoLocationRequest geoLocationRequest) {
        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setName(geoLocationRequest.getName());
        geoLocation.setAddress(geoLocationRequest.getAddress());
        double lon = geoLocationRequest.getLon();
        double lat = geoLocationRequest.getLat();


        // Create Point object using lat and lon
        Point location = new Point(lon, lat);

        geoLocation.setLocation(location);

        return geoLocation;
    }

    public List<GeoLocation> saveGeoLocations(List<GeoLocationRequest> geoLocationRequests) {
        // Convert the list of GeoLocationRequest to GeoLocation entities
        List<GeoLocation> geoLocations = geoLocationRequests.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        return StreamSupport.stream(geoLocationRepository.saveAll(geoLocations).spliterator(), false)
                .collect(Collectors.toList());
    }



    public List<GeoLocationResponse> getAllLocations(Pageable pageable) {
        // Assuming geoLocationRepository.findAllLocations() returns Iterable<GeoLocation>
        Iterable<GeoLocation> geoLocations = geoLocationRepository.findAll();


        return StreamSupport.stream(geoLocations.spliterator(), false)
                .map(this::convertToGeoLocationResponse)
                .collect(Collectors.toList());
    }



    private GeoLocationResponse convertToGeoLocationResponse(GeoLocation geoLocation) {
        System.out.println(geoLocation.getAddress());
        System.out.println(geoLocation.getLocation());
        return GeoLocationResponse.builder()
                .name(geoLocation.getName())
                .latitude(geoLocation.getLocation().getX())
                .longitude(geoLocation.getLocation().getY())
                .address(geoLocation.getAddress())
                .build();
    }

    public GeoLocation saveGeoLocation(GeoLocation geoLocation) {
        return geoLocationRepository.save(geoLocation);
    }


    public void deleteByGeoLocationId(String id) {
        // Logic to delete the GeoLocation by id
        geoLocationRepository.deleteById(id);
    }
    public void deleteAll() {
        // Logic to delete the GeoLocation by id
        geoLocationRepository.deleteAll();
    }
}
