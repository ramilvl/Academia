package com.project.OnlineLearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping(value = "/health")
    public ResponseEntity health() {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/readiness")
    public ResponseEntity readiness() {
        return ResponseEntity.ok().build();
    }
}
