package com.minihouse.request;

import com.minihouse.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpRequest {

    private String name;
    private String nickname;
    private String email;
    private String password;
    private String phone;

    public SignUpRequest() {

    }

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
