package com.thenightof.aiit.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String userId;  // 학번
    private String userName; // 사용자 이름
    private String password;  // 비밀번호
    private String scode;     // 추가적인 코드 (필요시)
}
