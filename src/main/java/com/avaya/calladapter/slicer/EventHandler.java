package com.avaya.calladapter.slicer;

import org.openapitools.model.Call;

public interface EventHandler {

    Runnable createCall(Call call);

    Runnable deleteCall(String callId);
}
