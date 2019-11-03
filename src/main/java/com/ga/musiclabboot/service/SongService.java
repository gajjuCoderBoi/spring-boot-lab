package com.ga.musiclabboot.service;

import com.ga.musiclabboot.model.Song;
import com.ga.musiclabboot.model.User;

import java.util.List;


public interface SongService {

    public Song createSong(Song song);
    public List<Song> songList();
    public Song updateSong(Song song, Long songId);
    public Long deleteSong(Long songId);
    public List<User> listeners(Long songId);

    public List<User> addListener(long songId, String username);

}
