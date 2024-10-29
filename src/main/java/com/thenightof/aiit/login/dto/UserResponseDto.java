package com.thenightof.aiit.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private boolean isSuccessed; // 성공 여부
    private String message;      // 메시지
    private String userName;     // 사용자 이름 반환용
}
