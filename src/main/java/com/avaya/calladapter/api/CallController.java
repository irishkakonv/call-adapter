package com.avaya.calladapter.api;

import com.avaya.calladapter.service.ExecutionService;
import com.avaya.calladapter.slicer.EventHandler;
import org.openapitools.api.V1Api;
import org.openapitools.model.Call;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class CallController implements V1Api {

    private EventHandler eventHandler;
    private ExecutionService executionService;

    public CallController(final ExecutionService executionService, final EventHandler eventHandler) {
        this.executionService = executionService;
        this.eventHandler = eventHandler;
    }

    @Override
    public ResponseEntity<String> createCall(final Call call) {
        executionService.run(eventHandler.createCall(call));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> deleteCall(final String callId) {
        executionService.run(eventHandler.deleteCall(callId));
        return ResponseEntity.ok().build();
    }
}
