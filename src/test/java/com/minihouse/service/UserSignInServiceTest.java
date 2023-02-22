package com.minihouse.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.minihouse.domain.User;
import com.minihouse.repository.UserRepository;
import com.minihouse.request.SignInRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserSignInServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Spy
    @InjectMocks
    private UserSignInService userSignInService;

    @Test
    @DisplayName("저장된 유저 정보와 유저가 요청한 정보가 일치하면 로그인에 성공한다.")
    void signIn() {

        // given
        User user = User.builder()
            .name("kim")
            .nickname("nick")
            .password("1234")
            .email("abc@test.com")
            .phone("010-0000-0000")
            .build();

        SignInRequest signInRequest = SignInRequest.builder()
            .email("abc@test.com")
            .password("1234")
            .build();

        given(userRepository.findByEmail(anyString())).willReturn(user);
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);

        // when
        userSignInService.signIn(signInRequest.getEmail(), signInRequest.getPassword());

        // then
        verify(userRepository).findByEmail(eq(signInRequest.getEmail()));
        verify(passwordEncoder).matches(eq(signInRequest.getPassword()), eq(user.getPassword()));
    }

    @Test
    @DisplayName("가입되어 있지 않은 이메일로 로그인 할 수 없다.")
    void canNotSignInDoesNotExistEmail() {

        // given
        SignInRequest signInRequest = SignInRequest.builder()
            .email("abc@test.com")
            .password("1234")
            .build();

        given(userRepository.findByEmail(anyString())).willReturn(null);

        // expected
        assertThatThrownBy(() -> userSignInService.signIn(signInRequest.getEmail() , signInRequest.getPassword()))
            .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("비밀번호를 잘못 입력한 경우 로그인 할 수 없다.")
    void canNotSignInInputWrongPassword() {

        // given
        User user = User.builder()
            .name("kim")
            .nickname("nick")
            .password("1234")
            .email("abc@test.com")
            .phone("010-0000-0000")
            .build();

        SignInRequest signInRequest = SignInRequest.builder()
            .email("abc@test.com")
            .password("5678")
            .build();

        given(userRepository.findByEmail(anyString())).willReturn(user);
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(false);

        // expected
        assertThatThrownBy(() -> userSignInService.signIn(signInRequest.getEmail() , signInRequest.getPassword()))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
