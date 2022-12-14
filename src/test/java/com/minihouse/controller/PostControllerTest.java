package com.minihouse.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minihouse.domain.Post;
import com.minihouse.request.PostCreateRequest;
import com.minihouse.request.PostUpdateRequest;
import com.minihouse.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureRestDocs
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    @Test
    @DisplayName("????????? ?????? api")
    void registerPost() throws Exception {
        // given
        PostCreateRequest request = PostCreateRequest.builder()
            .title("???????????????.")
            .content("???????????????.")
            .build();

        given(postService.create(any(Post.class))).willReturn(Long.valueOf(1));

        // when
        ResultActions result = mockMvc.perform(post("/api/v1/posts")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk())
            .andDo(document("post-create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("title").description("??????"),
                    fieldWithPath("content").description("??????"))))
            .andDo(print());
    }

    @Test
    @DisplayName("????????? ?????? api")
    void updatePost() throws Exception {
        // given
        PostUpdateRequest request = PostUpdateRequest.builder()
            .title("????????? ??????")
            .content("????????? ??????")
            .build();

        given(postService.create(any(Post.class))).willReturn(Long.valueOf(1));

        // when
        ResultActions result = mockMvc.perform(patch("/api/v1/posts/{id}", 1)
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk())
            .andDo(document("post-update",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("title").description("????????? ??????"),
                    fieldWithPath("content").description("????????? ??????"))))
            .andDo(print());
    }
}
