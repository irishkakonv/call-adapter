package com.avaya.calladapter.service.impl;

import com.avaya.calladapter.kafka.AvroConverter;
import com.avaya.calladapter.kafka.Producer;
import com.avaya.calladapter.service.CallService;
import org.openapitools.model.Call;
import org.springframework.stereotype.Service;

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
        kafkaProducer.send(avroConverter.createKafkaCreatedCall(call));
    }

    @Override
    public void deleteCall(final String callId) {
        kafkaProducer.send(avroConverter.createKafkaDeleteCall(callId));
    }
}
