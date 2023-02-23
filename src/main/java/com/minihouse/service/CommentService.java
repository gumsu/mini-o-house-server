package com.minihouse.service;

import com.minihouse.domain.Comment;
import com.minihouse.domain.Post;
import com.minihouse.repository.CommentRepository;
import com.minihouse.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long create(Comment comment) {
        verifyExistPost(comment);
        commentRepository.save(comment);
        return comment.getId();
    }

    private void verifyExistPost(Comment comment) {
        Post findPostById = postRepository.findById(comment.getPostId());
        if (findPostById == null) {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        }
    }
}
