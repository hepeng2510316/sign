package com.demo.hibernate.repository;

import com.demo.hibernate.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Integer>{

    List<Comment> findListByRecordId(Integer recordId);
}
