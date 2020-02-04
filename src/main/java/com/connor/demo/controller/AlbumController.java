package com.connor.demo.controller;

import com.connor.demo.model.album.Album;
import com.connor.demo.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AlbumController {


    private AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public ResponseEntity<Iterable<Album>> getAlbums() {
        return new ResponseEntity<>(albumService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/albums")
    public ResponseEntity<Album> addAlbum(HttpServletRequest request, @RequestBody Album album) {
        return new ResponseEntity<>(albumService.addAlbum(request, album), HttpStatus.OK);
    }


}
