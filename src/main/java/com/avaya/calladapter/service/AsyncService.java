package com.avaya.calladapter.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AsyncService {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void run(Runnable runnable) {
        CompletableFuture.runAsync(runnable, executorService);
    }
}
