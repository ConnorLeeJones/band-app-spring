package com.connor.demo.repository;

import com.connor.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


//    @Query("select a.id from user.userProfile.artists.a a")
//    Set<Long> getAllUserArtistIds();


    User findUserById(Long id);
    User findUserByUsername(String username);

    User findByUsername(String username);

    boolean existsByUsername(String username);
}