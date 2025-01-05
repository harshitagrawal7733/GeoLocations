package com.pegman.bars.geo_locations.controller;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.erhlc.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.client.erhlc.RestClients;

@Configuration
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {

    private final ElasticsearchProperties elasticsearchProperties;

    public ElasticsearchClientConfig(ElasticsearchProperties elasticsearchProperties) {
        this.elasticsearchProperties = elasticsearchProperties;
    }

    @Override
    @Bean(name = "customElasticsearchClient") // Renamed bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .withConnectTimeout(10000)
                .withSocketTimeout(elasticsearchProperties.getSocketTimeout())
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
