package com.ga.musiclabboot.service;

import com.ga.musiclabboot.model.Song;
import com.ga.musiclabboot.model.User;
import com.ga.musiclabboot.repository.SongRepository;
import com.ga.musiclabboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService{

    @Autowired
    SongRepository songRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public List<User> addListener(long songId, String username) {
        User user = userRepository.findByUsername(username);
        Song song = songRepository.findById(songId).orElse(null);
        song.addListener(user);
        return songRepository.save(song).getListeners();
    }
}
