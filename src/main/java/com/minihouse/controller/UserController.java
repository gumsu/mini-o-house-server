package com.minihouse.controller;

import com.minihouse.domain.User;
import com.minihouse.request.SignUpRequest;
import com.minihouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/ap1/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping()
    public Long signUp(@RequestBody SignUpRequest request) {
        User user = request.toEntity();
        return userService.signUp(user);
    }
}
