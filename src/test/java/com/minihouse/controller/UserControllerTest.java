package com.minihouse.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minihouse.domain.User;
import com.minihouse.request.SignInRequest;
import com.minihouse.request.SignUpRequest;
import com.minihouse.service.UserService;
import com.minihouse.service.UserSignInService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureRestDocs
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private UserSignInService userSignInService;

    User user;

    @BeforeEach
    void setUp() {
         user = User.builder()
             .id(1L)
             .name("kim")
             .email("abc@test.com")
             .password("1234")
             .phone("010-0000-0000")
             .build();
    }

    @Test
    @DisplayName("회원가입 api")
    void signUp() throws Exception {
        // given
        SignUpRequest request = SignUpRequest.builder()
            .name("kim")
            .nickname("nick")
            .password("1234")
            .email("abc@test.com")
            .phone("010-0000-0000")
            .build();

        given(userService.signUp(any(User.class))).willReturn(Long.valueOf(1));

        // when
        ResultActions result = mockMvc.perform(post("/api/v1/users/sign-up")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk())
            .andDo(document("user-signup",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("name").description("이름"),
                    fieldWithPath("nickname").description("닉네임"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phone").description("전화번호"))))
            .andDo(print());
    }

    @Test
    @DisplayName("로그인 api")
    void signIn() throws Exception {

        // given
        SignInRequest request = SignInRequest.builder()
            .email("abc@test.com")
            .password("1234")
            .build();

        MockHttpSession mockHttpSession = new MockHttpSession();

        given(userSignInService.signIn(anyString(), anyString())).willReturn(user);

        // when
        ResultActions result = mockMvc.perform(post("/api/v1/users/sign-in")
            .session(mockHttpSession)
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk())
            .andDo(document("user-signin",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("password").description("비밀번호"))))
            .andDo(print());
    }

    @Test
    @DisplayName("로그아웃 api")
    void signOut() throws Exception {
        // given
        SignInRequest request = SignInRequest.builder()
            .email("abc@test.com")
            .password("1234")
            .build();

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("USER_EMAIL", request.getEmail());

        given(userSignInService.signIn(anyString(), anyString())).willReturn(user);

        // when
        ResultActions result = mockMvc.perform(get("/api/v1/users/sign-out")
            .session(mockHttpSession));

        // then
        result.andExpect(status().isOk())
            .andDo(document("user-signout",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .andDo(print());
    }
}
