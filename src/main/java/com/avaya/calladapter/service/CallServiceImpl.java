package com.avaya.calladapter.service;

import com.avaya.calladapter.kafka.Producer;
import org.openapitools.model.Call;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CallServiceImpl implements CallService {

    private Producer kafkaProducer;

    public CallServiceImpl(final Producer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void createCall(final Call call) {
        Runnable createCall = () -> kafkaProducer.sendCreatedCall(call);
        CompletableFuture.runAsync(createCall);
    }

    @Override
    public void deleteCall(final String callId) {
        Runnable deleteCall = () -> kafkaProducer.sendDeletedCall(callId);
        CompletableFuture.runAsync(deleteCall);
    }
}
