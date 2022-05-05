package com.avaya.calladapter.api;

import com.avaya.calladapter.model.Call;
import com.avaya.calladapter.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@BasePathAwareController
@Validated
//@RestController
public class CallController {

    private CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }

    /**
     * POST /v1/calls
     * start a new call
     *
     * @param call call metadata (required)
     * @return Accepted (status code 202)
     * or Bad input (status code 400)
     */
    @PostMapping(path = "calls")
    public ResponseEntity<String> createCall(@Valid @RequestBody Call call) {
        return ResponseEntity.ok(callService.createCall(call));
    }

    /**
     * POST /v1/calls/{callId}
     * end call
     *
     * @param callId The unique 36 character id that represents the call (required)
     * @return Accepted (status code 202)
     * or Bad input (status code 400)
     */
    @PostMapping(path = "calls/{callId}")
    public ResponseEntity<String> deleteCall(
        @Size(min = 36, max = 36, message = "Id must contains 36 characters") @PathVariable("callId") String callId
    ) {
        return ResponseEntity.ok(callService.deleteCall(callId));
    }
}
