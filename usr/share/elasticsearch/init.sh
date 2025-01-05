#!/bin/bash

# Wait for Elasticsearch to be up and running
echo "Checking Elasticsearch status..."
until curl -s -X GET "http://localhost:9200/_cluster/health?pretty" | grep -q "\"status\" : \"yellow\|green\""; do
  echo "Waiting for Elasticsearch to be up..."
  sleep 2
done

echo "Elasticsearch is up and running!"

# Create the index and define schema (mapping)
echo "Creating the 'bars_geo_locations' index..."
curl -X PUT "localhost:9200/bars_geo_locations" -H 'Content-Type: application/json' -d'
{
  "mappings": {
    "properties": {
      "name": {
        "type": "text"
      },
      "address": {
        "type": "text"
      },
      "location": {
        "type": "geo_point"
      }
    }
  }
}
'
echo "Index 'bars_geo_locations' created successfully!"

# Insert default data
echo "Inserting default data..."


curl -X POST "localhost:9200/bars_geo_locations/_doc" -H 'Content-Type: application/json' -d'
{
  "name": "The French Loaf",
  "address": "42, A Block, Sector 1, Salt Lake City, Kolkata, West Bengal",
  "location": {
    "lat": 22.5705,
    "lon": 88.4314
  }
}
'
echo "Inserted data: Bhojohori Manna"

# Add more locations if needed
echo "Data insertion completed!"
