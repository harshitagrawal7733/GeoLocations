package com.pegman.bars.geo_locations.repo;

import com.pegman.bars.geo_locations.Entity.GeoLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface GeoLocationRepository extends ElasticsearchRepository<GeoLocation, String> {

    @Query("{\"bool\": {\"filter\": [{\"geo_distance\": {\"distance\": \"?0\", \"location\": {\"lon\": ?2, \"lat\": ?1}}}]}}")
    List<GeoLocation> findByLocationWithin(String distance, Double latitude, Double longitude);



    @Query("{\"_source\": [\"name\", \"address\", \"location\"], \"query\": {\"match_all\": {}}}")
    List<GeoLocation> findAllLocations();

    boolean existsByNameOrLocation(String name, GeoPoint location);
}
