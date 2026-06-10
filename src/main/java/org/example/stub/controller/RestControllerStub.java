package org.example.stub.controller;

import org.example.stub.service.DataBaseWorker;
import org.example.stub.service.DelayService;
import org.example.stub.dto.PostRequestDto;
import org.example.stub.dto.UserDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class RestControllerStub {

    private final DelayService delayService;
    private final DataBaseWorker dataBaseWorker;

    @GetMapping("/{login}")
    public ResponseEntity<UserDto> getData(@PathVariable String login) {
        delayService.applyRandomDelay();
        UserDto foundUser = dataBaseWorker.getUserByLogin(login);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> postData(@Valid @RequestBody PostRequestDto requestBody) {
        delayService.applyRandomDelay();
        UserDto createdUser = dataBaseWorker.createUser(requestBody);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

}
