package com.minihouse.repository;

import com.minihouse.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentRepository {

    void save(Comment comment);
}
