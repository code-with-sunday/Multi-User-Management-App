package com.sunday.Multi_User_Management_App.repository;

import com.sunday.Multi_User_Management_App.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
