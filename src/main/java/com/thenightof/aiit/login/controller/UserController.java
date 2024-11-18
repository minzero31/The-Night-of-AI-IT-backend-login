package com.thenightof.aiit.login.controller;

import com.thenightof.aiit.login.dto.UserRequestDto;
import com.thenightof.aiit.login.dto.UserResponseDto;
import com.thenightof.aiit.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// UserController에서 로그아웃 경로 제거
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto requestDto) {
        UserResponseDto response = userService.register(requestDto);
        return ResponseEntity.ok(response);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto requestDto, HttpServletRequest request) {
        UserResponseDto response = userService.login(requestDto);

        // 세션 생성 및 사용자 정보 저장
        HttpSession session = request.getSession(true); // 세션을 강제로 생성
        session.setAttribute("user", response); // 세션에 사용자 정보 저장

        return ResponseEntity.ok(response);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 기존 세션 가져오기
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return ResponseEntity.ok("로그아웃 성공");
    }
}
