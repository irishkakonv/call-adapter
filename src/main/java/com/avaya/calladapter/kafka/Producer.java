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

    public void sendCreatedCall(Call call) {
        send(createKafkaCreatedCall(call));
    }

    public void sendDeletedCall(String callId) {
        send(createKafkaDeleteCall(callId));
    }

    private void send(GenericRecord message) {
        ListenableFuture<SendResult<String, GenericRecord>> future = kafkaTemplate.send(kafkaTopic, message);

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

    private KafkaParticipant createKafkaParticipant(Participant participant) {
        return KafkaParticipant.newBuilder().setId(participant.getId()).setName(participant.getName()).build();
    }

    private KafkaCreateCall createKafkaCreatedCall(Call call) {
        return KafkaCreateCall.newBuilder()
            .setId(call.getId())
            .setCallerNumber(call.getCallerNumber())
            .setCalledNumber(call.getCalledNumber())
            .setEngagementDialogId(call.getEngagementDialogId())
            .setParticipant(createKafkaParticipant(call.getParticipant()))
            .setTimestamp(call.getTimestamp().toInstant())
            .build();
    }

    private KafkaDeleteCall createKafkaDeleteCall(String callId) {
        return KafkaDeleteCall.newBuilder().setId(callId).build();
    }
}
