package com.minihouse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import com.minihouse.domain.Post;
import com.minihouse.repository.PostRepository;
import com.minihouse.request.PostCreateRequest;
import com.minihouse.request.PostUpdateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    @DisplayName("게시글을 작성할 수 있다.")
    void create() {
        // given
        PostCreateRequest request = PostCreateRequest.builder()
            .title("제목입니다.")
            .content("내용입니다.")
            .build();
        Post post = request.toEntity();
        doNothing().when(postRepository).save(isA(Post.class));

        // when
        Long postId = postService.create(post);

        // then
        then(postRepository).should().save(post);
        assertThat(post.getId()).isEqualTo(postId);
    }

    @Test
    @DisplayName("게시글을 수정할 수 있다.")
    void update() {
        // given
        Post post = Post.builder()
            .id(1L)
            .title("제목입니다.")
            .content("내용입니다.")
            .build();

        PostUpdateRequest request = PostUpdateRequest.builder()
            .title("수정한 제목")
            .content("수정한 내용")
            .build();

        given(postRepository.findById(1L)).willReturn(post);

        // when
        postService.update(1L, request.getTitle(), request.getContent());

        // then
        Assertions.assertAll(
            () -> assertThat(post.getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(post.getContent()).isEqualTo(request.getContent())
        );
    }
}
