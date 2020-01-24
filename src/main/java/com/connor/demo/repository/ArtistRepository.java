package com.connor.demo.repository;

import com.connor.demo.model.Artist;
import com.connor.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

//    @Query("select a.id from artist a")
//    Set<Long> getAllArtistIds();

}
