package com.minihouse.service;

import com.minihouse.domain.User;
import com.minihouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long signUp(User user) {
        userRepository.save(user);
        return user.getId();
    }
}
