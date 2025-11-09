package com.account.aggregator.consentservice.controller;

import com.account.aggregator.consentservice.model.Consent;
import com.account.aggregator.consentservice.service.ConsentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consents")
public class ConsentController {

    private final ConsentService consentService;

    public ConsentController(ConsentService consentService) {
        this.consentService = consentService;
    }

    @PostMapping
    public ResponseEntity<Consent> createConsent(@RequestParam("consentId") String consentId,
                                                 @RequestParam("customerId") String customerId) {
        return ResponseEntity.ok(consentService.createConsent(consentId, customerId));
    }

    @GetMapping("/{consentId}")
    public ResponseEntity<Consent> getConsent(@PathVariable("consentId") String consentId) {
        return consentService.getConsent(consentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Consent>> getAllConsents() {
        return ResponseEntity.ok(consentService.getAllConsents());
    }

    @PutMapping("/revoke/{consentId}")
    public ResponseEntity<Consent> revokeConsent(@PathVariable("consentId") String consentId) {
        return ResponseEntity.ok(consentService.revokeConsent(consentId));
    }
}
