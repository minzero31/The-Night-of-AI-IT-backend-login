package com.thenightof.aiit.login.service;

import com.thenightof.aiit.login.domain.user.User;
import com.thenightof.aiit.login.repository.UserRepository;
import com.thenightof.aiit.login.dto.UserRequestDto;
import com.thenightof.aiit.login.dto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    // UserService.java
    public UserResponseDto register(UserRequestDto requestDto) {
        // scode 확인
        if (!requestDto.getScode().equals("ssdflkjlkj294")) {
            return new UserResponseDto(false, "유효하지 않은 인증 코드입니다.", null);
        }

        if (userRepository.existsByUserId(requestDto.getUserId())) {
            return new UserResponseDto(false, "이미 존재하는 아이디입니다.", null);
        }

        User user = User.builder()
                .userId(requestDto.getUserId())
                .userName(requestDto.getUserName())
                .password(requestDto.getPassword()) // 암호화된 비밀번호 사용
                .build();
        userRepository.save(user);

        return new UserResponseDto(true, "회원가입 성공", user.getUserName());
    }

    // 로그인 메서드
    public UserResponseDto login(UserRequestDto requestDto) {
        Optional<User> userOptional = userRepository.findByUserId(requestDto.getUserId());

        if (!userOptional.isPresent()) {
            return new UserResponseDto(false, "로그인 실패. 아이디와 비밀번호를 확인해주세요.", null);
        }

        User user = userOptional.get();
        if (!user.getPassword().equals(requestDto.getPassword())) {
            return new UserResponseDto(false, "로그인 실패. 아이디와 비밀번호를 확인해주세요.", null);
        }

        return new UserResponseDto(true, "로그인 성공", user.getUserName());
    }

    // UserDetailsService 인터페이스 구현
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
