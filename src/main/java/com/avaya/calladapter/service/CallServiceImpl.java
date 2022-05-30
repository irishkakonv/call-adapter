package com.avaya.calladapter.service;

import com.avaya.calladapter.kafka.AvroConverter;
import com.avaya.calladapter.kafka.Producer;
import org.openapitools.model.Call;
import org.springframework.stereotype.Service;

@Service
public class CallServiceImpl implements CallService {

    private final AvroConverter avroConverter;
    private final Producer kafkaProducer;
    private final AsyncService asyncService;

    public CallServiceImpl(final Producer kafkaProducer, final AvroConverter avroConverter, final AsyncService asyncService) {
        this.kafkaProducer = kafkaProducer;
        this.avroConverter = avroConverter;
        this.asyncService = asyncService;
    }

    @Override
    public void createCall(final Call call) {
        asyncService.run(() -> kafkaProducer.send(avroConverter.createKafkaCreatedCall(call)));
    }

    @Override
    public void deleteCall(final String callId) {
        asyncService.run(() -> kafkaProducer.send(avroConverter.createKafkaDeleteCall(callId)));
    }
}
