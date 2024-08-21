package com.sunday.Multi_User_Management_App.repository;

import com.sunday.Multi_User_Management_App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String username);
}
