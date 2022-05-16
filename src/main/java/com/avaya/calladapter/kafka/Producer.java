package com.avaya.calladapter.kafka;

import com.avaya.calladapter.KafkaCreateCall;
import com.avaya.calladapter.KafkaDeleteCall;
import com.avaya.calladapter.KafkaParticipant;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.openapitools.model.Call;
import org.openapitools.model.Participant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private String kafkaTopic;
    private KafkaProducer<String, GenericRecord> callKafkaProducer;

    public Producer(@Value("${kafka.topic}") String kafkaTopic, final KafkaProducer<String, GenericRecord> callKafkaProducer) {
        this.kafkaTopic = kafkaTopic;
        this.callKafkaProducer = callKafkaProducer;
    }

    public void sendCreatedCall(Call call) {
        send(createKafkaCreatedCall(call));
    }

    public void sendDeletedCall(String callId) {
        send(createKafkaDeleteCall(callId));
    }

    private void send(GenericRecord kafkaCall) {
        ProducerRecord<String, GenericRecord> producerRecord = new ProducerRecord<>(kafkaTopic, kafkaCall);
        callKafkaProducer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(final RecordMetadata recordMetadata, final Exception e) {
                if (e == null) {
                    LOGGER.info("Success. The message was send to " + recordMetadata.topic());
                }
                else {
                    e.printStackTrace();
                }
            }
        });
        callKafkaProducer.flush();
        callKafkaProducer.close();
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
        return KafkaDeleteCall.newBuilder()
            .setId(callId)
            .build();
    }
}
