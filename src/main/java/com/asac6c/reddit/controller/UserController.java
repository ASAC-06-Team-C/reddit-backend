package com.asac6c.reddit.controller;

import com.asac6c.reddit.dto.CreateUserRequestDto;
import com.asac6c.reddit.dto.LoginRequestDto;
import com.asac6c.reddit.dto.LoginResponseDto;
import com.asac6c.reddit.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> PostUser(
            @Valid @RequestBody CreateUserRequestDto createUserRequestDto
    ) {
        userService.userRegister(createUserRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("")
    public ResponseEntity<LoginResponseDto> userLogin(@Valid @RequestBody LoginRequestDto request) {
        LoginResponseDto body = userService.userLogin(request);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}