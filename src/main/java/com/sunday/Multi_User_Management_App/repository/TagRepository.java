package com.sunday.Multi_User_Management_App.repository;

import com.sunday.Multi_User_Management_App.enums.TagMark;
import com.sunday.Multi_User_Management_App.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByTagMark(TagMark tagMarks);

}
