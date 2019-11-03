package com.ga.musiclabboot.service;

import com.ga.musiclabboot.config.JwtUtil;
import com.ga.musiclabboot.model.Song;
import com.ga.musiclabboot.model.User;
import com.ga.musiclabboot.repository.SongRepository;
import com.ga.musiclabboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public String signup(User user) {
        user.setPassword(bCryptEncoder.encode(user.getPassword()));

        if(userRepository.save(user)!= null){
            UserDetails userDetails = loadUserByUsername(user.getUsername());

            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

    @Override
    public String login(User user) {
        User foundUser = userRepository.login(user.getUsername(), user.getPassword());

        if(foundUser != null &&
                bCryptEncoder.matches(user.getPassword(), foundUser.getPassword())){
            UserDetails userDetails = loadUserByUsername(user.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
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

    @Override
    public List<User> listUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User updateUser(User user, Long userId) {
        User savedUser = userRepository.findById(userId).orElse(null);
        savedUser.setPassword(user.getPassword());
        return savedUser;
    }

    @Override
    public Long deleteUser(Long userId) {
        User savedUser = userRepository.findById(userId).orElse(null);
        userRepository.delete(savedUser);
        return savedUser.getUserId();
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if(user == null) throw new UsernameNotFoundException("Username not found");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptEncoder.encode(user.getPassword()), true, true, true, true, getGrantedAuthority(user));
    }

    private List<GrantedAuthority> getGrantedAuthority(User user) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

        return grantedAuthorities;
    }
}
