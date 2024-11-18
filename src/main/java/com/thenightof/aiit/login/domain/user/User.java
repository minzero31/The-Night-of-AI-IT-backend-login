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

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId; // 학번 필드로 변경

    @Column(name = "user_name") // 사용자 이름 필드
    private String userName; // 사용자 이름 필드

    @Column(name = "password")
    private String password;

    @Builder
    public User(String userId, String userName, String password) { // userName을 포함
        this.userId = userId;
        this.userName = userName; // 사용자 이름 초기화
        this.password = password;
    }

    @Override // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // 사용자의 id 반환 (고유값)
    @Override
    public String getUsername() {
        return userId;
    }

    // 사용자의 패스워드 반환
    @Override
    public String getPassword() {
        return password;
    }

    // 사용자 이름 반환
    public String getUserName() {
        return userName;
    }

    // 계정 만료 여부, 계정 잠금 여부, 패스워드 만료 여부, 계정 사용 가능 여부 반환 메서드들 (기본 true)
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public void setPassword(String encodedPassword) {
    }
}
