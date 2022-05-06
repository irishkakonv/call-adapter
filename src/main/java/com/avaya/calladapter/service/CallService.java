package com.avaya.calladapter.service;

import org.openapitools.model.Call;

public interface CallService {

    String createCall(Call call);

    String deleteCall(String callId);

}
