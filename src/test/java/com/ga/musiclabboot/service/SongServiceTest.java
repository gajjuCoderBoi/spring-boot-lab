package com.ga.musiclabboot.service;

import com.ga.musiclabboot.model.Song;
import com.ga.musiclabboot.repository.SongRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class SongServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private SongRepository songRepository;

    @InjectMocks
    private  SongServiceImpl songService;

    @InjectMocks
    private Song song;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void initializeDummySong() {
        song.setSongId(1);
        song.setTitle("xyz");
        song.setLength(1);
    }

    @Test
    public void createSong() {
        when(songRepository.save(any())).thenReturn(song);

        assertEquals(song.getTitle(), songService.createSong(song).getTitle());
    }

    @Test
    public void updateSong() {
        when(songRepository.save(any())).thenReturn(song);

        assertEquals(song, songService.updateSong(song, song.getSongId()));
    }

    @Test
    public void deleteSong() {

        assertEquals(java.util.Optional.of(1L), java.util.Optional.of(songService.deleteSong(1L)));
    }
}
