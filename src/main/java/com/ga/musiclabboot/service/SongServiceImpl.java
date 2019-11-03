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
    public List<Song> songList() {
        return (List<Song>) songRepository.findAll();
    }

    @Override
    public Song updateSong(Song song, Long songId) {
        Song savedSong = songRepository.findById(songId).orElse(null);
        savedSong.setTitle(song.getTitle());
        savedSong.setLength(song.getLength());
        return savedSong;
    }

    @Override
    public Long deleteSong(Long songId) {
        Song savedSong = songRepository.findById(songId).orElse(null);
        songRepository.delete(savedSong);
        return savedSong.getSongId();
    }

    @Override
    public List<User> listeners(Long songId) {
        Song savedSong = songRepository.findById(songId).orElse(null);
        return savedSong.getListeners();
    }

    @Override
    public List<User> addListener(long songId, String username) {
        User user = userRepository.findByUsername(username);
        Song song = songRepository.findById(songId).orElse(null);
        song.addListener(user);
        return songRepository.save(song).getListeners();
    }
}
