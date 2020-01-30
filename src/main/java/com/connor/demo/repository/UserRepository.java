package com.connor.demo.repository;

import com.connor.demo.model.User;
import com.connor.demo.model.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findUserById(Long id);
    User findUserByUsername(String username);


    @Query(value = "SELECT new com.connor.demo.model.UserDto(u.id, u.username) FROM User u")
    Iterable<UserDto> findAllUserDto();

    @Query(value = "SELECT * FROM Music.user u WHERE u.username REGEXP ?1",
    nativeQuery = true)
    Iterable<User> searchUsers(String searchTerm);


    User findByUsername(String username);

    boolean existsByUsername(String username);
}