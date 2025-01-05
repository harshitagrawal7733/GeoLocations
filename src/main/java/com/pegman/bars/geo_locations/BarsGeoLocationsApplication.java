package com.pegman.bars.geo_locations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class BarsGeoLocationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarsGeoLocationsApplication.class, args);
	}

}
