package com.avaya.calladapter.api;

import com.avaya.calladapter.service.CallService;
import org.openapitools.api.V1Api;
import org.openapitools.model.Call;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class CallController implements V1Api {

    private final CallService callService;

    public CallController(final CallService callService) {
        this.callService = callService;
    }

    @Override
    public ResponseEntity<String> createCall(final Call call) {
        callService.createCall(call);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> deleteCall(final String callId) {
        callService.deleteCall(callId);
        return ResponseEntity.ok().build();
    }
}
