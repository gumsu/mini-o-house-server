package com.minihouse.request;

import com.minihouse.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpRequest {

    private final String name;
    private final String nickname;
    private final String email;
    private final String password;
    private final String phone;

    @Builder
    public SignUpRequest(String name, String nickname, String email, String password, String phone) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User toEntity() {
        return User.builder()
            .name(name)
            .nickname(nickname)
            .email(email)
            .password(password)
            .phone(phone)
            .build();
    }
}
