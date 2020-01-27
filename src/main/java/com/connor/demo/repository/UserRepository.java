package com.connor.demo.repository;

import com.connor.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findUserById(Long id);
    User findUserByUsername(String username);

    User findByUsername(String username);

    boolean existsByUsername(String username);
}