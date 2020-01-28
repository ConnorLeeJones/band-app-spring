package com.connor.demo.repository;

import com.connor.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findUserById(Long id);
    User findUserByUsername(String username);

//    @Query(value = "SELECT u.user_id, u.username FROM Music.user u")
//    Iterable<User> findAllUserIdAndUsername();

    User findByUsername(String username);

    boolean existsByUsername(String username);
}