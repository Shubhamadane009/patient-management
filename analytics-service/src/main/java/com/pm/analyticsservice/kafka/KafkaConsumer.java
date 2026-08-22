package com.pm.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    //here we need to specify the topic which we want to consume from the event
    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumeEvent(byte[] event){

        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            //..perform any business logic realted to analytics here
            log.info("Received patientEvent : [patientID={}, patientName={}, patientEmail={}, ]",
                    patientEvent.getPatientId(),patientEvent.getName(),patientEvent.getEmail());

        } catch (InvalidProtocolBufferException e) {
            log.error("Error consuming/reading/deserializing event in analytic-service: {}",e.getMessage());
        }
    }
}
