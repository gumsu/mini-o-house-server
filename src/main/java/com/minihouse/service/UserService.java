package com.minihouse.service;

import com.minihouse.domain.User;
import com.minihouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(User user) {
        validSignUpUser(user);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.convertToEncryptedPassword(encodedPassword);

        userRepository.save(user);

        return user.getId();
    }

    private void validSignUpUser(User user) {
        User findUser = userRepository.findByEmail(user.getEmail());
        if (findUser != null) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }
}
