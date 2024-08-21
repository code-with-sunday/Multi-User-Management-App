package com.sunday.Multi_User_Management_App.repository;

import com.sunday.Multi_User_Management_App.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
