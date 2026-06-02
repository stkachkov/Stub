package org.example.stub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class DelayService {
    private static final Logger logger = LoggerFactory.getLogger(DelayService.class);

    @Value("${stub.delay.base-ms:1000}")
    private long baseDelayMs;

    @Value("${stub.delay.random-ms:1000}")
    private int randomDelayMs;

    private final Random random = new Random();

    public void applyRandomDelay() {
        try {
            long delay = baseDelayMs + (randomDelayMs > 0 ? random.nextInt(randomDelayMs) : 0);
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Процесс задержки был прерван", e);
            throw new RuntimeException("Процесс задержки был прерван", e);
        }
    }
}
