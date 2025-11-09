package com.account.aggregator.consentservice.repository;

import com.account.aggregator.consentservice.model.Consent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsentRepository extends JpaRepository<Consent, Long> {
    Optional<Consent> findByConsentId(String consentId);
}
