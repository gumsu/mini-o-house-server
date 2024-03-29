package com.minihouse.service;

import com.minihouse.domain.User;
import com.minihouse.exception.PasswordNotMatchedException;
import com.minihouse.exception.UserNotFoundException;
import com.minihouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserSignInService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signIn(String email, String password) {
        return validSignInUser(email, password);
    }

    private User validSignInUser(String email, String password) {
        User findUser = userRepository.findByEmail(email);
        if (findUser == null) {
            throw new UserNotFoundException("존재하지 않는 회원입니다.");
        }
        boolean isMatched = passwordEncoder.matches(password, findUser.getPassword());
        if (!isMatched) {
            throw new PasswordNotMatchedException("비밀번호가 다릅니다.");
        }
        return findUser;
    }
}
