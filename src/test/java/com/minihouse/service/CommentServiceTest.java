package com.minihouse.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.minihouse.domain.Comment;
import com.minihouse.domain.Post;
import com.minihouse.repository.CommentRepository;
import com.minihouse.repository.PostRepository;
import com.minihouse.request.CommentCreateRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    @DisplayName("댓글을 작성할 수 있다.")
    void create() {
        // given
        Long postId = 1L;
        CommentCreateRequest request = CommentCreateRequest.builder()
            .content("테스트 댓글")
            .userId(1L)
            .build();

        Post post = Post.builder()
            .id(1L)
            .title("제목입니다.")
            .content("내용입니다.")
            .build();

        Comment comment = request.toEntity(postId);

        given(postRepository.findById(postId)).willReturn(post);
        doNothing().when(commentRepository).save(isA(Comment.class));

        // when

        Long commentId = commentService.create(comment);

        // then
        verify(commentRepository).save(comment);
        Assertions.assertThat(comment.getId()).isEqualTo(commentId);
    }

    @Test
    @DisplayName("존재하지 않는 게시글에는 댓글을 달 수 없다.")
    void canNotCommentOnPostThatDoesNotExist() {
        // given
        Long postId = 1L;
        CommentCreateRequest request = CommentCreateRequest.builder()
            .content("테스트 댓글")
            .userId(1L)
            .build();

        Comment comment = request.toEntity(postId);

        given(postRepository.findById(postId)).willReturn(null);

        // expected
        assertThatThrownBy(() -> commentService.create(comment))
            .isInstanceOf(IllegalArgumentException.class);
    }
}