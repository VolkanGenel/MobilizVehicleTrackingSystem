package com.volkan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {
    @GetMapping("/user")
    public ResponseEntity<String> userServiceFallback() {
        return ResponseEntity.ok("User service şu anda hizmet verememektedir.");
    }
    @GetMapping("/vehicle")
    public ResponseEntity<String> vehicleServiceFallback() {
        return ResponseEntity.ok("Vehicle service şu anda hizmet verememektedir.");
    }
    @GetMapping("/authorization")
    public ResponseEntity<String> authorizationServiceFallback() {
        return ResponseEntity.ok("DistrictAndAuthorization service şu anda hizmet verememektedir.");
    }
    @GetMapping("/sector")
    public ResponseEntity<String> sectorServiceFallback() {
        return ResponseEntity.ok("DistrictAndAuthorization service şu anda hizmet verememektedir.");
    }
    @GetMapping("/zone")
    public ResponseEntity<String> zoneServiceFallback() {
        return ResponseEntity.ok("DistrictAndAuthorization service şu anda hizmet verememektedir.");
    }
}
