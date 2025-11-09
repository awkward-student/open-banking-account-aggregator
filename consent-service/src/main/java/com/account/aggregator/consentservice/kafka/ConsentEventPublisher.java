package com.account.aggregator.consentservice.kafka;

import com.account.aggregator.common.event.ConsentEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;

    public ConsentEventPublisher(KafkaTemplate<String, Object> kafkaTemplate,
                                 @Value("${spring.kafka.topic.consent-events}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publishEvent(ConsentEvent event) {
        kafkaTemplate.send(topic, event);
        System.out.println("📡 Published Kafka event: " + event);
    }
}
