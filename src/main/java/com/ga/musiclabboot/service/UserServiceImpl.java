package com.ga.musiclabboot.service;

import com.ga.musiclabboot.model.Song;
import com.ga.musiclabboot.model.User;
import com.ga.musiclabboot.repository.SongRepository;
import com.ga.musiclabboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Override
    public User login(String username, String password) {
        return userRepository.login(username, password);
    }

    @Override
    public User signup(User user) {
        return userRepository.save(user);
    }


    @Override
    public List<Song> addSong(Long id, String username) {
        User user = userRepository.findByUsername(username);
        Song song = songRepository.findById(id).orElse(null);
        user.addSong(song);
        userRepository.save(user);
        return user.getSongs();
    }

    @Override
    public List<Song> listSongs(String username) {
        return userRepository.findByUsername(username).getSongs();

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
