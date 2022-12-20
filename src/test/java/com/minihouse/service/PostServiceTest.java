package com.minihouse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import com.minihouse.domain.Post;
import com.minihouse.repository.PostRepository;
import com.minihouse.request.PostCreateRequest;
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
}
