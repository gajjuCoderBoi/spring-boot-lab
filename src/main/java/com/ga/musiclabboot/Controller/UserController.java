package com.ga.musiclabboot.Controller;

import com.ga.musiclabboot.model.JwtResponse;
import com.ga.musiclabboot.model.Song;
import com.ga.musiclabboot.model.User;
import com.ga.musiclabboot.service.SongService;
import com.ga.musiclabboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SongService songService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.signup(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
    }

    @PutMapping("/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable(value = "userId") Long userId) {
        return userService.updateUser(user, userId); }

    @DeleteMapping("/{userId}")
    public Long deleteUser(@PathVariable(value = "userId") Long userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/{username}/songs")
    public List<Song> getUserSongs(@PathVariable(value = "username") String username) {
        return userService.listSongs(username);
    }

    @PutMapping("/{userId}/addsong/{songId}")
    public List<Song> addUserSong(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "songId") Long songId)
    {
        return userService.addSong(userId, String.valueOf(songId));
    }

}
