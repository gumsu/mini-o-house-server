package com.minihouse.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInRequest {

    private String email;
    private String password;

    public SignInRequest() {
    }

    @Builder
    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
