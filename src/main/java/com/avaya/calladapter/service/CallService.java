package com.avaya.calladapter.service;

import com.avaya.calladapter.model.Call;

public interface CallService {

    String createCall(Call call);

    String deleteCall(String callId);

}
