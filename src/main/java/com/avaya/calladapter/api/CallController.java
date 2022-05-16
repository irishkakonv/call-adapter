package com.avaya.calladapter.api;

import com.avaya.calladapter.service.CallService;
import org.openapitools.api.V1ApiController;
import org.openapitools.api.V1ApiDelegate;
import org.openapitools.model.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class CallController extends V1ApiController {

    private final CallService callService;

    public CallController(
        @Autowired(required = false) final V1ApiDelegate delegate, final CallService callService
    ) {
        super(delegate);
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
