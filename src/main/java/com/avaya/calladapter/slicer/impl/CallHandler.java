package com.avaya.calladapter.slicer.impl;

import com.avaya.calladapter.service.CallService;
import com.avaya.calladapter.slicer.EventHandler;
import org.openapitools.model.Call;
import org.springframework.stereotype.Service;

@Service
public class CallHandler implements EventHandler {

    private CallService callService;

    public CallHandler(final CallService callService) {
        this.callService = callService;
    }

    @Override
    public Runnable createCall(final Call call) {
       return () -> callService.createCall(call);
    }

    @Override
    public Runnable deleteCall(final String callId) {
        return () -> callService.deleteCall(callId);
    }
}
