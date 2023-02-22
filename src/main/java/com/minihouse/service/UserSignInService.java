package com.minihouse.service;

import com.minihouse.domain.User;
import com.minihouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserSignInService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signIn(String email, String password) {
        validSignInUser(email, password);
    }

    private void validSignInUser(String email, String password) {
        User findUser = userRepository.findByEmail(email);
        if (findUser == null) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        boolean isMatched = passwordEncoder.isMatched(password, findUser.getPassword());
        if (!isMatched) {
            throw new IllegalArgumentException("비밀번호가 다릅니다.");
        }
    }
}
