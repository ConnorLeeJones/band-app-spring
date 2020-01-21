package com.connor.demo.repository;

import com.connor.demo.model.Artist;
import com.connor.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {


}
