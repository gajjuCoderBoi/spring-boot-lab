package com.ga.musiclabboot.service;

import com.ga.musiclabboot.model.Song;
import com.ga.musiclabboot.model.User;

import java.util.List;

public interface UserService {
    public User login(String username, String password);

    public User signup(User user);

    public List<Song> addSong(Long id, String username);

    public List<Song> listSongs(String username);

    public User findByUsername(String username);
}
