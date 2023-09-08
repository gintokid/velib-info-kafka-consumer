package com.madroid.velibdataconsume;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.madroid.velibdataconsume.jpa.model.Station;
import com.madroid.velibdataconsume.jpa.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StationInfoTopicListener {

    @Autowired
    StationRepository repository;

    @KafkaListener(topics = "velibdata-station-info", groupId = "velibdata")
    void consume(String data) throws JsonProcessingException {
        System.out.println("Listener received data from topic velibdata-station-info : " + data.length() + " characters... 👍");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode respNode = objectMapper.readTree(data);
        JsonNode stationNodes = respNode.get("data").get("stations");
        List<Station> stations = new ArrayList<>();

        for (JsonNode stationNode : stationNodes) {


            Station station = Station.builder()
                    .id(stationNode.get("station_id").asLong())
                    .name(stationNode.get("name").asText())
                    .code(stationNode.get("stationCode").asText())
                    .capacity(stationNode.get("capacity").asLong())
                    .latitude(stationNode.get("lat").asDouble())
                    .longitude(stationNode.get("lon").asDouble())
                    .build();

            stations.add(station);
        }

        System.out.println("Saving " + stations.size() + " stations info IN PROGRESS");
        repository.saveAll(stations);
        System.out.println("Saving " + stations.size() + " stations info DONE");

        System.exit(0);
    }
}
