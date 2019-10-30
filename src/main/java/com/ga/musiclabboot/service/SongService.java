package com.ga.musiclabboot.service;

import com.ga.musiclabboot.model.Song;
import com.ga.musiclabboot.model.User;

import java.util.List;


public interface SongService {

    public Song createSong(Song song);

    public List<User> addListener(long songId, String username);

}
