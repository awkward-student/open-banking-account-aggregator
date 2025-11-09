package com.account.aggregator.consentservice.service;

import com.account.aggregator.common.event.ConsentEvent;
import com.account.aggregator.consentservice.kafka.ConsentEventPublisher;
import com.account.aggregator.consentservice.model.Consent;
import com.account.aggregator.consentservice.repository.ConsentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsentService {

    @Autowired
    private ConsentEventPublisher eventPublisher;

    private final ConsentRepository consentRepository;

    public ConsentService(ConsentRepository consentRepository) {
        this.consentRepository = consentRepository;
    }

    public Consent createConsent(String consentId, String customerId) {
        Consent consent = new Consent();
        consent.setConsentId(consentId);
        consent.setCustomerId(customerId);
        consent.setStatus("ACTIVE");
        consent.setCreatedAt(LocalDateTime.now());
        consent.setExpiresAt(LocalDateTime.now().plusMonths(1));

        consentRepository.save(consent);
        eventPublisher.publishEvent(new ConsentEvent(consent.getConsentId(), consent.getCustomerId(), "GRANTED"));
        return consent;
    }

    public Optional<Consent> getConsent(String consentId) {
        return consentRepository.findByConsentId(consentId);
    }

    public List<Consent> getAllConsents() {
        return consentRepository.findAll();
    }

    public Consent revokeConsent(String consentId) {
        Optional<Consent> consentOpt = consentRepository.findByConsentId(consentId);
        if (consentOpt.isPresent()) {
            Consent consent = consentOpt.get();
            consent.setStatus("REVOKED");
            consentRepository.save(consent);
            eventPublisher.publishEvent(new ConsentEvent(consent.getConsentId(), consent.getCustomerId(), "REVOKED"));
            return consent;
        }
        throw new RuntimeException("Consent not found with ID: " + consentId);
    }
}
