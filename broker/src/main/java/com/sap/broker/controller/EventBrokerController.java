package com.sap.broker.controller;

import com.sap.broker.service.BrokerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class EventBrokerController {

    private BrokerService brokerService;

    public EventBrokerController(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    @GetMapping("/api/demo/tasks")
    public ResponseEntity<String> getData() {
        String data = brokerService.getData();

        return new ResponseEntity(data, HttpStatus.OK);
    }
}
