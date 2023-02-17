package com.minihouse.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    @DisplayName("게시글 등록 api")
    void registerPost() throws Exception {
        // given
        PostCreateRequest request = PostCreateRequest.builder()
            .title("제목입니다.")
            .content("내용입니다.")
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
                    fieldWithPath("title").description("제목"),
                    fieldWithPath("content").description("내용"))))
            .andDo(print());
    }

    @Test
    @DisplayName("게시글 수정 api")
    void updatePost() throws Exception {
        // given
        PostUpdateRequest request = PostUpdateRequest.builder()
            .title("수정한 제목")
            .content("수정한 내용")
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
                    fieldWithPath("title").description("수정한 제목"),
                    fieldWithPath("content").description("수정한 내용"))))
            .andDo(print());
    }

    @Test
    @DisplayName("게시글 삭제 api")
    void deletePost() throws Exception {
        // given
        PostCreateRequest request = PostCreateRequest.builder()
            .title("제목입니다.")
            .content("내용입니다.")
            .build();

        given(postService.create(any(Post.class))).willReturn(Long.valueOf(1));

        // when
        ResultActions result = mockMvc.perform(delete("/api/v1/posts/{id}", 1)
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk())
            .andDo(document("post-delete",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("title").description("제목"),
                    fieldWithPath("content").description("내용"))))
            .andDo(print());
    }
}
