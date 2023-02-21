package com.minihouse.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import com.minihouse.domain.User;
import com.minihouse.repository.UserRepository;
import com.minihouse.request.SignInRequest;
import com.minihouse.request.SignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Spy
    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("회원가입을 할 수 있다.")
    void signUp() {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
            .name("kim")
            .nickname("nick")
            .password("1234")
            .email("abc@test.com")
            .phone("010-0000-0000")
            .build();
        User user = signUpRequest.toEntity();

        doNothing().when(userRepository).save(isA(User.class));

        // when
        Long userId = userService.signUp(user);

        // then
        then(userRepository).should().save(user);
        assertThat(user.getId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("이미 존재하는 이메일인 경우 회원가입을 할 수 없다.")
    void canNotSignUpExistEmail() {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
            .name("kim")
            .nickname("nick")
            .password("1234")
            .email("abc@test.com")
            .phone("010-0000-0000")
            .build();
        User user = signUpRequest.toEntity();

        given(userRepository.findByEmail(any(String.class))).willReturn(user);

        // expected
        assertThatThrownBy(() -> userService.signUp(user))
            .isInstanceOf(IllegalArgumentException.class);

        then(userRepository).should().findByEmail(user.getEmail());
        then(userRepository).should(never()).save(user);
    }

    @Test
    @DisplayName("로그인을 할 수 있다.")
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
        given(passwordEncoder.isMatched(anyString(), anyString())).willReturn(true);

        // when
        userService.signIn(signInRequest.getEmail(), signInRequest.getPassword());

        // then
        verify(userRepository).findByEmail(eq(signInRequest.getEmail()));
        verify(passwordEncoder).isMatched(eq(signInRequest.getPassword()), eq(user.getPassword()));
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
        assertThatThrownBy(() -> userService.signIn(signInRequest.getEmail() , signInRequest.getPassword()))
            .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("비밀번호를 잘못 입력한 경우 로그인할 수 없다.")
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
        given(passwordEncoder.isMatched(anyString(), anyString())).willReturn(false);

        // expected
        assertThatThrownBy(() -> userService.signIn(signInRequest.getEmail() , signInRequest.getPassword()))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
