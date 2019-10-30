package com.ga.musiclabboot.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @Column(name = "song_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long songId;

    @Column
    private String title;

    @Column
    private int length;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_song",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> listeners;

    public List<User> addListener(User user){
        if(listeners==null) listeners = new ArrayList<>();
        listeners.add(user);
        return listeners;
    }

    public Song(long songId, String title, int length, List<User> listeners) {
        this.songId = songId;
        this.title = title;
        this.length = length;
        this.listeners = listeners;
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<User> getListeners() {
        return listeners;
    }

    public void setListeners(List<User> listeners) {
        this.listeners = listeners;
    }

    public Song() {
    }

    public Song(long songId, String title, int length) {
        this.songId = songId;
        this.title = title;
        this.length = length;
    }
}
