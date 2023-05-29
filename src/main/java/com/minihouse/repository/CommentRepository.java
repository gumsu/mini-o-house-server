package com.minihouse.repository;

import com.minihouse.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentRepository {

    void save(Comment comment);

    Comment findById(Long id);

    void update(Comment comment);

    void delete(Comment comment);
}
