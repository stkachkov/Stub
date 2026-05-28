package org.example.stub.controller;

import jakarta.validation.Valid;
import org.example.stub.dto.GetResponseDto;
import org.example.stub.dto.PostRequestDto;
import org.example.stub.dto.PostResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/data")
public class RestControllerStub {

    private static final Logger logger = LoggerFactory.getLogger(RestControllerStub.class);

    @Value("${stub.delay.base-ms:1000}")
    private long baseDelayMs;

    @Value("${stub.delay.random-ms:1000}")
    private int randomDelayMs;

    private final Random random = new Random();

    private void applyRandomDelay() {
        try {
            long delay = baseDelayMs + (randomDelayMs > 0 ? random.nextInt(randomDelayMs) : 0);
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            logger.warn("Процесс задержки был прерван", e);
            throw new RuntimeException("Процесс задержки был прерван", e);
        }
    }

    @GetMapping
    public ResponseEntity<GetResponseDto> getData() {
        applyRandomDelay();

        GetResponseDto response = new GetResponseDto("Login1", "ok");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> postData(@Valid @RequestBody PostRequestDto requestBody) {
        applyRandomDelay();

        String login = requestBody.getLogin();
        String password = requestBody.getPassword();
        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        PostResponseDto response = new PostResponseDto(login, password, currentDateTime);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
