package com.thenightof.aiit.login.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User implements UserDetails {
    @Id
    @Column(name = "user_id", nullable = false, length = 10, unique = true)
    private String userId; // 학번

    @Column(name = "user_name", nullable = false, length = 5)
    private String userName; // 실명

    @Column(name = "user_pw", nullable = false, length = 30)
    private String userPw; // 비밀번호

    @Column(name = "user_scode", nullable = false, length = 10)
    private String userScode; // 입장코드, 모든 사용자가 동일 코드 소유

    // 생성자에 기본 입장 코드를 추가하고, Builder 패턴으로 필요한 필드만 설정
    @Builder
    public User(String userId, String userName, String userPw, String userScode) {
        this.userId = userId;
        this.userName = userName;
        this.userPw = userPw;
        this.userScode = userScode != null ? userScode : "DEFAULT_CODE"; // 기본 입장 코드 설정

    }

    @Override // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    //사용자의 id 반환 (고유값)
    @Override
    public String getUsername() {
        return userId;
    }

    //사용자의 패스워드 반환
    @Override
    public String getPassword() {
        return userPw;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true; // true -> 만료되지 않았음
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true; //true -> 잠금되지 않았음
    }

    //패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true; //true -> 만료되지 않았음
    }

    //계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true; //true -> 사용 가능
    }
}
