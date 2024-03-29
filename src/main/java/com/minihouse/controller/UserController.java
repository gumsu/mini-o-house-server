package com.minihouse.controller;

import com.minihouse.domain.User;
import com.minihouse.request.SignInRequest;
import com.minihouse.request.SignUpRequest;
import com.minihouse.service.UserService;
import com.minihouse.service.UserSignInService;
import com.minihouse.vo.AuthUserVO;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;
    private final UserSignInService userSignInService;

    @PostMapping("/sign-up")
    public Long signUp(@RequestBody SignUpRequest request) {
        User user = request.toEntity();
        return userService.signUp(user);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<HttpRequest> signIn(@RequestBody SignInRequest request, HttpSession session) {
        User user = userSignInService.signIn(request.getEmail(), request.getPassword());

        AuthUserVO authUserVO = AuthUserVO.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .build();

        session.setAttribute("AUTH_USER", authUserVO);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/sign-out")
    public ResponseEntity<HttpRequest> signOut(HttpSession session) {
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
