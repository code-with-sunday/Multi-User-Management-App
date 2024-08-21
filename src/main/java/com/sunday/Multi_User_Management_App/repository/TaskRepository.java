package com.sunday.Multi_User_Management_App.repository;

import com.sunday.Multi_User_Management_App.enums.TagMark;
import com.sunday.Multi_User_Management_App.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByTags_TagMarkIn(List<TagMark> tagMarks, Pageable pageable);

}
