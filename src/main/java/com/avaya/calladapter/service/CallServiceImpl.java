package com.avaya.calladapter.service;

import com.avaya.calladapter.kafka.AvroConverter;
import com.avaya.calladapter.kafka.Producer;
import org.openapitools.model.Call;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CallServiceImpl implements CallService {

    private final AvroConverter avroConverter;
    private final Producer kafkaProducer;

    public CallServiceImpl(final Producer kafkaProducer, final AvroConverter avroConverter) {
        this.kafkaProducer = kafkaProducer;
        this.avroConverter = avroConverter;
    }

    @Override
    public void createCall(final Call call) {
        Runnable createCall = () -> kafkaProducer.send(avroConverter.createKafkaCreatedCall(call));
        CompletableFuture.runAsync(createCall);
    }

    @Override
    public void deleteCall(final String callId) {
        Runnable deleteCall = () -> kafkaProducer.send(avroConverter.createKafkaDeleteCall(callId));
        CompletableFuture.runAsync(deleteCall);
    }
}
