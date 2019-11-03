package com.ga.musiclabboot.Controller;

import com.ga.musiclabboot.model.Song;
import com.ga.musiclabboot.model.User;
import com.ga.musiclabboot.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {

    @Autowired
    SongService songService;

    @GetMapping("/list")
    public List<Song> songList() {
        return songService.songList();
    }

    @PostMapping("/create")
    public Song create(@RequestBody Song song){
        return songService.createSong(song);
    }

    @PutMapping("/{songId}")
    public Song update(@RequestBody Song song, @PathVariable(value = "songId") Long songId){
        return songService.updateSong(song, songId);
    }

    @DeleteMapping("/{songId}")
    public long delete(@PathVariable(value = "songId") Long songId){
        return songService.deleteSong(songId);
    }

    @GetMapping("/{songId}/listeners")
    public List<User> listeners(@PathVariable(value = "songId") Long songId){
        return songService.listeners(songId);
    }



}
