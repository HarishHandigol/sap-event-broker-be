package com.sap.broker.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import net.minidev.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class EventBrokerController {

    @GetMapping("/api/demo/tasks")
    public ResponseEntity<String> getData(@RequestParam Map<String, String> params) {
        File input = new File("/Users/harish.handigol/Downloads/BoulderTrailHeads.csv");
        try {
            CsvSchema csv = CsvSchema.emptySchema().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            MappingIterator<Map<String, String>> mappingIterator =  csvMapper.reader().forType(Map.class).with(csv).readValues(input);
            List<Map<String, String>> list = mappingIterator.readAll();

            return new ResponseEntity(JSONArray.toJSONString(list), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception while fetching the events");
        }
    }
}
