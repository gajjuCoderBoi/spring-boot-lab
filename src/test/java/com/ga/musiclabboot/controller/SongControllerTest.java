package com.ga.musiclabboot.controller;

import com.ga.musiclabboot.Controller.SongController;
import com.ga.musiclabboot.service.SongService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)

public class SongControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private SongController songController;

    @Mock
    private SongService songService;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(songController).build();
    }
    @Test
    public void create() throws Exception {
        create_Song_Success();
        create_Song_Fail();
    }

    @Test
    public void update() throws Exception {
        update_Song_Success();
        update_Song_Fail();
    }

    @Test
    public void delete() throws Exception {
        delete_Song_Success();
        delete_Song_Fail();

    }

    public void create_Song_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/songs/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createSongInJson("abc", 4));

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }
    public void create_Song_Fail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/songs/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("sadfbg");


        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
    public void update_Song_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/songs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createSongInJson("abc", 4));


        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }
    public void update_Song_Fail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/songs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("sadfbg");


        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
    public void delete_Song_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/songs/1")
                .contentType(MediaType.APPLICATION_JSON);

        when(songService.deleteSong(anyLong())).thenReturn(1L);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }
    public void delete_Song_Fail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/songs/wrq")
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    private static String createSongInJson(String name, int length) {
        return "{ \"name\": \"" + name + "\", " +
                "\"length\":\"" + length + "\"}";
    }

}