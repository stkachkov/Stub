package org.example.stub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/data")
public class RestControllerStub {

    @GetMapping
    public ResponseEntity<Map<String, String>> getData() throws InterruptedException {
        long delay = 1000 + (long) (Math.random() * 1000);
        TimeUnit.MILLISECONDS.sleep(delay);

        Map<String, String> response = new LinkedHashMap<>();
        response.put("login", "Login1");
        response.put("status", "ok");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> postData(@RequestBody Map<String, String> requestBody) throws InterruptedException {
        long delay = 1000 + (long) (Math.random() * 1000);
        TimeUnit.MILLISECONDS.sleep(delay);

        String login = requestBody.get("login");
        String password = requestBody.get("password");

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            Map<String, String> errorResponse = new LinkedHashMap<>();
            errorResponse.put("error", "Login and password are required");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Map<String, String> response = new LinkedHashMap<>();
        response.put("login", login);
        response.put("password", password);
        response.put("date", currentDateTime);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
