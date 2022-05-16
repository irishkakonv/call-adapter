package com.avaya.calladapter.kafka;

import com.avaya.calladapter.KafkaCreateCall;
import com.avaya.calladapter.KafkaDeleteCall;
import com.avaya.calladapter.KafkaParticipant;
import org.openapitools.model.Call;
import org.openapitools.model.Participant;
import org.springframework.stereotype.Service;

@Service
public class AvroConverter {

    private KafkaParticipant createKafkaParticipant(Participant participant) {
        return KafkaParticipant.newBuilder()
            .setId(participant.getId())
            .setName(participant.getName())
            .build();
    }

    public KafkaCreateCall createKafkaCreatedCall(Call call) {
        return KafkaCreateCall.newBuilder()
            .setId(call.getId())
            .setCallerNumber(call.getCallerNumber())
            .setCalledNumber(call.getCalledNumber())
            .setEngagementDialogId(call.getEngagementDialogId())
            .setParticipant(createKafkaParticipant(call.getParticipant()))
            .setTimestamp(call.getTimestamp().toInstant())
            .build();
    }

    public KafkaDeleteCall createKafkaDeleteCall(String callId) {
        return KafkaDeleteCall.newBuilder()
            .setId(callId)
            .build();
    }
}
