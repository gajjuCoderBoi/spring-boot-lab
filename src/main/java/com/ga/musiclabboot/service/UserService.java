package com.ga.musiclabboot.service;

import com.ga.musiclabboot.model.Song;
import com.ga.musiclabboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public String login(User user);

    public String signup(User user);

    public List<Song> addSong(Long id, String username);

    public List<Song> listSongs(String username);

    public User findByUsername(String username);

    public List<User> listUsers();

    public User updateUser(User user, Long userId);
    public Long deleteUser(Long userId);
}
