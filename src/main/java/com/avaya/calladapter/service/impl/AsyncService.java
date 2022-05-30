package com.avaya.calladapter.service.impl;

import com.avaya.calladapter.service.ExecutionService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AsyncService implements ExecutionService {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void run(Runnable runnable) {
        CompletableFuture.runAsync(runnable, executorService);
    }
}
