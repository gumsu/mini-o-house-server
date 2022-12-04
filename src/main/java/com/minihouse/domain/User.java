package com.minihouse.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {

    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public User(Long id, String name, String nickname, String email, String password,
        String phone, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
