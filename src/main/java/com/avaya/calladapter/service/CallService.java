package com.avaya.calladapter.service;

import org.openapitools.model.Call;

public interface CallService {

    void createCall(Call call);

    void deleteCall(String callId);

}
