package com.sap.broker.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import net.minidev.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class BrokerServiceImpl implements BrokerService {
    @Override
    public String getData() {

        try {
            File input = ResourceUtils.getFile("classpath:BoulderTrailHeads.csv");
            CsvSchema csv = CsvSchema.emptySchema().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            MappingIterator<Map<String, String>> mappingIterator =  csvMapper.reader().forType(Map.class).with(csv).readValues(input);
            List<Map<String, String>> list = mappingIterator.readAll();

            return JSONArray.toJSONString(list);
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception while fetching the events");
        }
    }
}
