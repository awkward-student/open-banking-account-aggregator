package com.account.aggregator.aggregatorservice.kafka;

import com.account.aggregator.common.event.ConsentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsentEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(ConsentEventConsumer.class);

    @KafkaListener(topics = "consent-events", groupId = "aggregator-service")
    public void consume(ConsentEvent event) {
        if (event == null) {
            log.warn("Received null ConsentEvent from Kafka. Ignoring.");
            return;
        }

        log.info("[Kafka] Received ConsentEvent → consentId={}, customerId={}, status={}",
                event.getConsentId(), event.getCustomerId(), event.getStatus());

        try {
            log.info("Processing consent event for customer '{}', status '{}'",
                    event.getCustomerId(), event.getStatus());

            log.info("✅ Successfully processed ConsentEvent: {}", event.getConsentId());
        } catch (Exception e) {
            log.error("Error processing ConsentEvent (id={}): {}", event.getConsentId(), e.getMessage(), e);
        }
    }
}
