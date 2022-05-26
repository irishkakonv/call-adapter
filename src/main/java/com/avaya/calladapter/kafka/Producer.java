package com.avaya.calladapter.kafka;

import com.avaya.calladapter.KafkaCreateCall;
import com.avaya.calladapter.KafkaDeleteCall;
import com.avaya.calladapter.KafkaParticipant;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.openapitools.model.Call;
import org.openapitools.model.Participant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private String kafkaTopic;
    private KafkaTemplate<String, GenericRecord> kafkaTemplate;

    public Producer(
        @Value("${kafka.topic}") String kafkaTopic, final KafkaTemplate<String, GenericRecord> kafkaTemplate
    ) {
        this.kafkaTopic = kafkaTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(GenericRecord message) {
        ListenableFuture<SendResult<String, GenericRecord>> future = kafkaTemplate.send(kafkaTopic, "id", message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, GenericRecord>>() {

            @Override
            public void onSuccess(SendResult<String, GenericRecord> result) {
                LOGGER.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.info("Unable to send message=[{}] due to : {}", message, ex.getMessage());
            }
        });
    }
}
