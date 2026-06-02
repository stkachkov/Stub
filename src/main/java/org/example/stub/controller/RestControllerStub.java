package org.example.stub.controller;

import org.example.stub.service.DelayService;
import org.example.stub.dto.GetResponseDto;
import org.example.stub.dto.PostRequestDto;
import org.example.stub.dto.PostResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class RestControllerStub {

    private final DelayService delayService;

    @GetMapping
    public ResponseEntity<GetResponseDto> getData() {
        delayService.applyRandomDelay();

        GetResponseDto response = new GetResponseDto("Login1", "ok");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> postData(@Valid @RequestBody PostRequestDto requestBody) {
        delayService.applyRandomDelay();

        String login = requestBody.getLogin();
        String password = requestBody.getPassword();
        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        PostResponseDto response = new PostResponseDto(login, password, currentDateTime);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
