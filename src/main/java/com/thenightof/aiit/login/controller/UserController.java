package com.thenightof.aiit.login.controller;

import com.thenightof.aiit.login.dto.UserRequestDto;
import com.thenightof.aiit.login.dto.UserResponseDto;
import com.thenightof.aiit.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto requestDto) {
        // 비밀번호를 그대로 저장
        UserResponseDto response = userService.register(requestDto); // 수정된 부분
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto requestDto) {
        UserResponseDto response = userService.login(requestDto);
        return ResponseEntity.ok(response);
    }
}
